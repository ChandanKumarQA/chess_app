package com.chessmaster.play.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BillingManager(private val context: Context) : PurchasesUpdatedListener {

    private lateinit var billingClient: BillingClient
    
    private val _isBillingConnected = MutableStateFlow(false)
    val isBillingConnected: StateFlow<Boolean> = _isBillingConnected.asStateFlow()

    private val _productDetails = MutableStateFlow<List<ProductDetails>>(emptyList())
    val productDetails: StateFlow<List<ProductDetails>> = _productDetails.asStateFlow()
    
    // Callback to pass purchased hint amounts back to UI
    var onHintsPurchased: ((Int) -> Unit)? = null

    // Callback to pass purchased room amounts back to UI
    var onRoomsPurchased: ((Int) -> Unit)? = null

    // Placeholder Product IDs. The user must configure these in Google Play Console.
    val productIds = listOf(
        "hints_10",
        "hints_45",
        "hints_125",
        "hints_200",
        "rooms_5",
        "rooms_10"
    )

    init {
        setupBillingClient()
    }

    private fun setupBillingClient() {
        val pendingPurchasesParams = PendingPurchasesParams.newBuilder()
            .enableOneTimeProducts()
            .build()
        billingClient = BillingClient.newBuilder(context)
            .setListener(this)
            .enablePendingPurchases(pendingPurchasesParams)
            .build()
            
        startConnection()
    }

    private fun startConnection() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    _isBillingConnected.value = true
                    queryProductDetails()
                } else {
                    Log.e("BillingManager", "Billing setup failed: ${billingResult.debugMessage}")
                }
            }

            override fun onBillingServiceDisconnected() {
                _isBillingConnected.value = false
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    private fun queryProductDetails() {
        val productList = productIds.map { productId ->
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(productId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        }

        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()

        billingClient.queryProductDetailsAsync(params) { billingResult, result ->
            val productDetailsList = result.productDetailsList
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && productDetailsList != null) {
                _productDetails.value = productDetailsList
            }
        }
    }

    fun launchPurchaseFlow(activity: Activity, productId: String) {
        val productDetails = _productDetails.value.find { it.productId == productId }
        
        if (productDetails != null) {
            val productDetailsParamsList = listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .build()
            )

            val billingFlowParams = BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build()

            billingClient.launchBillingFlow(activity, billingFlowParams)
        } else {
            Log.e("BillingManager", "ProductDetails not found for $productId")
            // Ideally show a toast here to the user
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
            Log.i("BillingManager", "User cancelled purchase")
        } else {
            // Handle any other error codes.
            Log.e("BillingManager", "Error on purchase: ${billingResult.debugMessage}")
        }
    }

    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            // Grant entitlement to the user.
            for (productId in purchase.products) {
                when (productId) {
                    "hints_10" -> onHintsPurchased?.invoke(10)
                    "hints_45" -> onHintsPurchased?.invoke(45)
                    "hints_125" -> onHintsPurchased?.invoke(125)
                    "hints_200" -> onHintsPurchased?.invoke(200)
                    "rooms_5" -> onRoomsPurchased?.invoke(5)
                    "rooms_10" -> onRoomsPurchased?.invoke(10)
                }
            }
            
            // Acknowledge the purchase if it hasn't already been acknowledged.
            if (!purchase.isAcknowledged) {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                billingClient.acknowledgePurchase(acknowledgePurchaseParams.build()) { billingResult ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        Log.i("BillingManager", "Purchase acknowledged")
                    }
                }
            }
        }
    }
}
