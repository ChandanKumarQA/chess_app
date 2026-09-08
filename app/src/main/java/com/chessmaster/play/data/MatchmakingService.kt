package com.chessmaster.play.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import android.util.Log

class MatchmakingService(private val firestore: FirebaseFirestore) {

    suspend fun createGame(userId: String, timeControlName: String): FirebaseGame? {
        return try {
            val docRef = firestore.collection("games").document()
            val game = FirebaseGame(
                id = docRef.id,
                whitePlayerId = userId,
                status = "waiting",
                timeControlName = timeControlName
            )
            docRef.set(game).await()
            game
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error creating game", e)
            null
        }
    }

    suspend fun findRandomGame(userId: String): FirebaseGame? {
        return try {
            val snapshot = firestore.collection("games")
                .whereEqualTo("status", "waiting")
                .limit(1)
                .get()
                .await()

            if (snapshot.isEmpty) {
                return null
            }

            val doc = snapshot.documents.first()
            val game = doc.toObject(FirebaseGame::class.java)
            if (game != null && game.whitePlayerId != userId) {
                // Join the game
                val updatedGame = game.copy(
                    blackPlayerId = userId,
                    status = "in_progress",
                    lastMoveTimestamp = System.currentTimeMillis()
                )
                firestore.collection("games").document(game.id).set(updatedGame).await()
                updatedGame
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error finding game", e)
            null
        }
    }
    
    suspend fun joinGameByCode(gameId: String, userId: String): FirebaseGame? {
        return try {
            val docRef = firestore.collection("games").document(gameId)
            val doc = docRef.get().await()
            if (doc.exists()) {
                val game = doc.toObject(FirebaseGame::class.java)
                if (game != null && game.status == "waiting" && game.whitePlayerId != userId) {
                    val updatedGame = game.copy(
                        blackPlayerId = userId,
                        status = "in_progress",
                        lastMoveTimestamp = System.currentTimeMillis()
                    )
                    docRef.set(updatedGame).await()
                    updatedGame
                } else {
                    null
                }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error joining game", e)
            null
        }
    }
    suspend fun createRoomWithCode(userId: String, timeControlName: String, colorPreference: String = "Random"): FirebaseGame {
        try {
            val roomCode = generateRoomCode()
            val docRef = firestore.collection("games").document()
            val game = FirebaseGame(
                id = docRef.id,
                roomCode = roomCode,
                hostId = userId,
                status = "lobby",
                timeControlName = timeControlName,
                colorPreference = colorPreference,
                hostReady = true
            )
            kotlinx.coroutines.withTimeout(5000) {
                docRef.set(game).await()
            }
            return game
        } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
            throw Exception("Database timeout! Please check your internet or Firebase Firestore rules.")
        } catch (e: Exception) {
            throw Exception(e.message ?: "Unknown error")
        }
    }

    suspend fun joinRoomWithCode(roomCode: String, userId: String): FirebaseGame {
        val snapshot = firestore.collection("games")
            .whereEqualTo("roomCode", roomCode.uppercase())
            .limit(1)
            .get()
            .await()

        if (snapshot.isEmpty) {
            throw Exception("Invalid Room Code")
        }

        val doc = snapshot.documents.first()
        val game = doc.toObject(FirebaseGame::class.java)
            ?: throw Exception("Invalid Room Code")

        if (game.hostId == userId) {
            throw Exception("You cannot join your own room")
        }
        if (game.status != "lobby") {
            throw Exception("Game is already in progress (status: ${game.status})")
        }
        if (game.guestId != null) {
            throw Exception("Room is already full")
        }

        val updatedGame = game.copy(guestId = userId)
        firestore.collection("games").document(game.id).set(updatedGame).await()
        return updatedGame
    }

    private fun generateRoomCode(): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..6)
            .map { allowedChars.random() }
            .joinToString("")
    }

    suspend fun toggleReady(gameId: String, isHost: Boolean, isReady: Boolean) {
        try {
            val field = if (isHost) "hostReady" else "guestReady"
            firestore.collection("games").document(gameId).update(field, isReady).await()
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error toggling ready", e)
        }
    }

    suspend fun updateLobbySettings(gameId: String, timeControlName: String, colorPreference: String) {
        try {
            firestore.collection("games").document(gameId).update(
                "timeControlName", timeControlName,
                "colorPreference", colorPreference
            ).await()
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error updating settings", e)
        }
    }

    suspend fun startGame(gameId: String, colorPref: String) {
        try {
            val doc = firestore.collection("games").document(gameId).get().await()
            val game = doc.toObject(FirebaseGame::class.java) ?: return

            val isHostWhite = when(colorPref) {
                "White" -> true
                "Black" -> false
                else -> Math.random() > 0.5
            }
            val whiteId = if (isHostWhite) game.hostId else game.guestId
            val blackId = if (isHostWhite) game.guestId else game.hostId
            firestore.collection("games").document(gameId).update(
                "status", "starting",
                "whitePlayerId", whiteId,
                "blackPlayerId", blackId
            ).await()
        } catch (e: Exception) {
            Log.e("MatchmakingService", "Error starting game", e)
        }
    }
}
