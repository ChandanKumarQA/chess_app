package com.chessmaster.play.ui.screens

import android.app.Activity
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.chessmaster.play.RewardedAdManager

@Composable
fun BuyRoomsDialog(
    adManager: RewardedAdManager,
    activity: Activity?,
    onDismiss: () -> Unit,
    onRoomsEarned: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF5C2B09)) // Darker wood brown color
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
                        text = "Get Rooms",
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

                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "You have reached your limit of free rooms. Watch a short ad to continue playing with friends!",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Items
                RoomAdItemRow(
                    title = "3 ROOMS", 
                    buttonColor = Color(0xFF00ACC1),
                    borderColor = Color(0xFF00838F),
                    adManager = adManager, 
                    activity = activity,
                    rewardAmount = 3,
                    onRoomsEarned = onRoomsEarned
                )
                HorizontalDivider(color = Color(0xFF3E1D04), thickness = 1.dp)
                
                RoomAdItemRow(
                    title = "5 ROOMS", 
                    buttonColor = Color(0xFF4CAF50),
                    borderColor = Color(0xFF388E3C),
                    adManager = adManager, 
                    activity = activity,
                    rewardAmount = 5,
                    onRoomsEarned = onRoomsEarned
                )
            }
        }
    }
}

@Composable
fun RoomAdItemRow(
    title: String,
    buttonColor: Color,
    borderColor: Color,
    adManager: RewardedAdManager,
    activity: Activity?,
    rewardAmount: Int,
    onRoomsEarned: (Int) -> Unit
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
                        onRoomsEarned(rewardAmount)
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
