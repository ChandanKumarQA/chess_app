package com.chessmaster.play.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import android.util.Log

data class ChatMessage(
    val senderId: String = "",
    val text: String = "",
    val timestamp: Long = 0L
)

data class EmojiReaction(
    val senderId: String = "",
    val emoji: String = "",
    val timestamp: Long = 0L
)

data class FirebaseGame(
    val id: String = "",
    val roomCode: String = "",
    val hostId: String? = null,
    val guestId: String? = null,
    val whitePlayerId: String? = null,
    val blackPlayerId: String? = null,
    val status: String = "waiting", // waiting, lobby, starting, in_progress, finished, abandoned
    val fen: String = "",
    val lastMoveTimestamp: Long = 0L,
    val whiteTimeRemaining: Long = 0L,
    val blackTimeRemaining: Long = 0L,
    val timeControlName: String = "UNLIMITED",
    val colorPreference: String = "Random",
    val hostReady: Boolean = false,
    val guestReady: Boolean = false,
    val chatMessages: List<ChatMessage> = emptyList(),
    val emojiReactions: List<EmojiReaction> = emptyList(),
    val drawOfferedBy: String? = null,
    val rematchRequestedBy: String? = null,
    val winner: String? = null,
    val reason: String? = null
)

class FirebaseManager {
    private val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    val currentUserId: String?
        get() = auth.currentUser?.uid

    var lastAuthError: String? = null

    suspend fun signInAnonymously(): String? {
        if (auth.currentUser != null) {
            return auth.currentUser!!.uid
        }
        return try {
            val result = auth.signInAnonymously().await()
            lastAuthError = null
            result.user?.uid
        } catch (e: Exception) {
            Log.e("FirebaseManager", "Error signing in anonymously", e)
            lastAuthError = e.message ?: "Unknown error"
            null
        }
    }
}
