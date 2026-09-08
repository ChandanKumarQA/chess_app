package com.chessmaster.play.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chessmaster.play.RewardedAdManager
@Composable
fun ShopDialog(
    adManager: RewardedAdManager,
    onDismiss: () -> Unit,
    onHintsEarned: (Int) -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF5C2B09)) // Darker wood brown color matching screenshot
                .border(2.dp, Color(0xFFFFD700), RoundedCornerShape(12.dp)) // Gold border
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(24.dp))
                    Text(
                        text = "Shop",
                        color = Color(0xFFFFD700), // Gold
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color(0xFF8B4513), // Wood color for close
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onDismiss() }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Items
                ShopAdItemRow(title = "1 HINT", buttonColor = Color(0xFF00ACC1), borderColor = Color(0xFF00838F), adManager = adManager, activity = activity, rewardAmount = 1, onHintsEarned = onHintsEarned)
                HorizontalDivider(color = Color(0xFF3E1D04), thickness = 1.dp)
                
                val greenColor = Color(0xFF4CAF50)
                val greenBorder = Color(0xFF388E3C)
                
                ShopAdItemRow(title = "10 HINTS", buttonColor = greenColor, borderColor = greenBorder, adManager = adManager, activity = activity, rewardAmount = 10, onHintsEarned = onHintsEarned)
            }
        }
    }
}

@Composable
fun ShopAdItemRow(
    title: String,
    buttonColor: Color,
    borderColor: Color,
    adManager: RewardedAdManager,
    activity: Activity?,
    rewardAmount: Int,
    onHintsEarned: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        
        Button(
            onClick = {
                if (activity != null) {
                    adManager.showAd(activity) { _ ->
                        // Override ad unit reward with local reward amount
                        onHintsEarned(rewardAmount)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(buttonColor, buttonColor.copy(alpha = 0.7f))
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Watch an ad",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ShopPurchaseItemRow(
    title: String,
    buttonColor: Color,
    borderColor: Color,
    priceText: String,
    onPurchaseClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        
        Button(
            onClick = onPurchaseClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(buttonColor, buttonColor.copy(alpha = 0.7f))
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, borderColor, RoundedCornerShape(8.dp))
        ) {
            Text(
                text = priceText,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
