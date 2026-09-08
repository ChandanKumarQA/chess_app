package com.chessmaster.play.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.util.Log

data class OpeningMove(
    val san: String, // Standard Algebraic Notation
    val white: Int,
    val draws: Int,
    val black: Int
) {
    val totalGames: Int get() = white + draws + black
}

data class ExplorerTopGame(
    val id: String,
    val whiteName: String,
    val whiteRating: Int,
    val blackName: String,
    val blackRating: Int,
    val result: String,
    val date: String
)

data class ExplorerData(
    val openingName: String?,
    val moves: List<OpeningMove>,
    val topGames: List<ExplorerTopGame>
)

class OpeningExplorerApi {

    suspend fun getOpeningMoves(fen: String): List<OpeningMove> {
        return getExplorerData(fen).moves
    }

    suspend fun getExplorerData(fen: String): ExplorerData {
        return withContext(Dispatchers.IO) {
            val encodedFen = java.net.URLEncoder.encode(fen, "UTF-8")
            val urlString = "https://explorer.lichess.ovh/masters?fen=$encodedFen"
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()

                    parseResponse(response.toString())
                } else {
                    Log.e("OpeningExplorer", "Failed to fetch openings, response code: ${connection.responseCode}")
                    ExplorerData(null, emptyList(), emptyList())
                }
            } catch (e: Exception) {
                Log.e("OpeningExplorer", "Error fetching openings", e)
                ExplorerData(null, emptyList(), emptyList())
            }
        }
    }

    private fun parseResponse(jsonStr: String): ExplorerData {
        val moves = mutableListOf<OpeningMove>()
        val topGames = mutableListOf<ExplorerTopGame>()
        var openingName: String? = null

        try {
            val jsonObject = JSONObject(jsonStr)
            val openingObj = jsonObject.optJSONObject("opening")
            openingName = openingObj?.optString("name")

            val movesArray = jsonObject.optJSONArray("moves")
            if (movesArray != null) {
                for (i in 0 until movesArray.length()) {
                    val moveObj = movesArray.getJSONObject(i)
                    moves.add(
                        OpeningMove(
                            san = moveObj.getString("san"),
                            white = moveObj.getInt("white"),
                            draws = moveObj.getInt("draws"),
                            black = moveObj.getInt("black")
                        )
                    )
                }
            }

            val gamesArray = jsonObject.optJSONArray("topGames")
            if (gamesArray != null) {
                for (i in 0 until gamesArray.length()) {
                    val g = gamesArray.getJSONObject(i)
                    val whiteObj = g.optJSONObject("white")
                    val blackObj = g.optJSONObject("black")
                    val winner = g.optString("winner", "")
                    val result = when (winner) {
                        "white" -> "1-0"
                        "black" -> "0-1"
                        else -> "½-½"
                    }
                    val month = g.optString("month", g.optString("year", ""))
                    topGames.add(
                        ExplorerTopGame(
                            id = g.optString("id", "$i"),
                            whiteName = whiteObj?.optString("name") ?: "Unknown",
                            whiteRating = whiteObj?.optInt("rating") ?: 0,
                            blackName = blackObj?.optString("name") ?: "Unknown",
                            blackRating = blackObj?.optInt("rating") ?: 0,
                            result = result,
                            date = month
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("OpeningExplorer", "Error parsing response", e)
        }
        return ExplorerData(openingName, moves, topGames)
    }
}
