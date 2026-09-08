package com.chessmaster.play.data

import android.content.Context
import android.content.SharedPreferences

class RoomCreditManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("room_credits_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_FREE_CREATED = "free_rooms_created"
        private const val KEY_FREE_JOINED = "free_rooms_joined"
        private const val KEY_PURCHASED_ROOMS = "purchased_rooms"
        
        const val MAX_FREE_ROOMS = 3
    }

    val freeRoomsCreated: Int
        get() = prefs.getInt(KEY_FREE_CREATED, 0)

    val freeRoomsJoined: Int
        get() = prefs.getInt(KEY_FREE_JOINED, 0)

    val purchasedRooms: Int
        get() = prefs.getInt(KEY_PURCHASED_ROOMS, 0)

    fun canCreateRoom(): Boolean {
        return freeRoomsCreated < MAX_FREE_ROOMS || purchasedRooms > 0
    }

    fun canJoinRoom(): Boolean {
        return freeRoomsJoined < MAX_FREE_ROOMS || purchasedRooms > 0
    }

    fun consumeCreateRoomCredit() {
        if (freeRoomsCreated < MAX_FREE_ROOMS) {
            prefs.edit().putInt(KEY_FREE_CREATED, freeRoomsCreated + 1).apply()
        } else if (purchasedRooms > 0) {
            prefs.edit().putInt(KEY_PURCHASED_ROOMS, purchasedRooms - 1).apply()
        }
    }

    fun consumeJoinRoomCredit() {
        if (freeRoomsJoined < MAX_FREE_ROOMS) {
            prefs.edit().putInt(KEY_FREE_JOINED, freeRoomsJoined + 1).apply()
        } else if (purchasedRooms > 0) {
            prefs.edit().putInt(KEY_PURCHASED_ROOMS, purchasedRooms - 1).apply()
        }
    }

    fun addPurchasedRooms(amount: Int) {
        prefs.edit().putInt(KEY_PURCHASED_ROOMS, purchasedRooms + amount).apply()
    }
}
