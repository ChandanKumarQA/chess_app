package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object LucenaDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Lucena Bridge on B-file", "k7/8/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b6 shielding the king.", "Play a6b6 to begin the bridge.", listOf("a6b6", "a8b8", "b6c6")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b6 shielding the king.", "Play a6b6 to begin the bridge.", listOf("a6b6", "b8c8", "b6a7")),
        RawLesson("Lucena Bridge on B-file", "8/6k1/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b7 shielding the king.", "Play a6b7 to begin the bridge.", listOf("a6b7", "g7h8", "b7c8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b7 shielding the king.", "Play a6b7 to begin the bridge.", listOf("a6b7", "g8h8", "b7c8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b7 shielding the king.", "Play a6b7 to begin the bridge.", listOf("a6b7", "h7h8", "b7c8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/K7/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a6b7 shielding the king.", "Play a6b7 to begin the bridge.", listOf("a6b7", "h8g8", "b7c8")),
        RawLesson("Lucena Bridge on B-file", "8/K5k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a7b8 shielding the king.", "Play a7b8 to begin the bridge.", listOf("a7b8", "g7h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "6k1/K7/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a7b8 shielding the king.", "Play a7b8 to begin the bridge.", listOf("a7b8", "g8h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "8/K6k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a7b8 shielding the king.", "Play a7b8 to begin the bridge.", listOf("a7b8", "h7h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "7k/K7/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a7b8 shielding the king.", "Play a7b8 to begin the bridge.", listOf("a7b8", "h8g8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "K7/6k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a8b8 shielding the king.", "Play a8b8 to begin the bridge.", listOf("a8b8", "g7h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "K5k1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a8b8 shielding the king.", "Play a8b8 to begin the bridge.", listOf("a8b8", "g8h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "K7/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a8b8 shielding the king.", "Play a8b8 to begin the bridge.", listOf("a8b8", "h7h8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "K6k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with a8b8 shielding the king.", "Play a8b8 to begin the bridge.", listOf("a8b8", "h8g8", "b8c8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/1K6/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b6c6 shielding the king.", "Play b6c6 to begin the bridge.", listOf("b6c6", "b8c8", "c6d6")),
        RawLesson("Lucena Bridge on B-file", "8/6k1/1K6/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b6c7 shielding the king.", "Play b6c7 to begin the bridge.", listOf("b6c7", "g7h8", "c7d8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/1K6/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b6c7 shielding the king.", "Play b6c7 to begin the bridge.", listOf("b6c7", "g8h8", "c7d8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/1K6/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b6c7 shielding the king.", "Play b6c7 to begin the bridge.", listOf("b6c7", "h7h8", "c7d8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/1K6/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b6c7 shielding the king.", "Play b6c7 to begin the bridge.", listOf("b6c7", "h8g8", "c7d8")),
        RawLesson("Lucena Bridge on B-file", "8/1K4k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b7c8 shielding the king.", "Play b7c8 to begin the bridge.", listOf("b7c8", "g7h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "6k1/1K6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b7c8 shielding the king.", "Play b7c8 to begin the bridge.", listOf("b7c8", "g8h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "8/1K5k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b7c8 shielding the king.", "Play b7c8 to begin the bridge.", listOf("b7c8", "h7h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "7k/1K6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b7c8 shielding the king.", "Play b7c8 to begin the bridge.", listOf("b7c8", "h8g8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "1K6/6k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b8c8 shielding the king.", "Play b8c8 to begin the bridge.", listOf("b8c8", "g7h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "1K4k1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b8c8 shielding the king.", "Play b8c8 to begin the bridge.", listOf("b8c8", "g8h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "1K6/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b8c8 shielding the king.", "Play b8c8 to begin the bridge.", listOf("b8c8", "h7h8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "1K5k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with b8c8 shielding the king.", "Play b8c8 to begin the bridge.", listOf("b8c8", "h8g8", "c8d8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/2K5/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c6d7 shielding the king.", "Play c6d7 to begin the bridge.", listOf("c6d7", "b8b7", "d7e8")),
        RawLesson("Lucena Bridge on B-file", "8/6k1/2K5/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c6d7 shielding the king.", "Play c6d7 to begin the bridge.", listOf("c6d7", "g7h8", "d7e8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/2K5/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c6d7 shielding the king.", "Play c6d7 to begin the bridge.", listOf("c6d7", "g8h8", "d7e8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/2K5/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c6d7 shielding the king.", "Play c6d7 to begin the bridge.", listOf("c6d7", "h7h8", "d7e8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/2K5/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c6d7 shielding the king.", "Play c6d7 to begin the bridge.", listOf("c6d7", "h8g8", "d7e8")),
        RawLesson("Lucena Bridge on B-file", "8/2K3k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c7d8 shielding the king.", "Play c7d8 to begin the bridge.", listOf("c7d8", "g7h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "6k1/2K5/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c7d8 shielding the king.", "Play c7d8 to begin the bridge.", listOf("c7d8", "g8h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "8/2K4k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c7d8 shielding the king.", "Play c7d8 to begin the bridge.", listOf("c7d8", "h7h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "7k/2K5/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c7d8 shielding the king.", "Play c7d8 to begin the bridge.", listOf("c7d8", "h8g8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "2K5/6k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c8d8 shielding the king.", "Play c8d8 to begin the bridge.", listOf("c8d8", "g7h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "2K3k1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c8d8 shielding the king.", "Play c8d8 to begin the bridge.", listOf("c8d8", "g8h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "2K5/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c8d8 shielding the king.", "Play c8d8 to begin the bridge.", listOf("c8d8", "h7h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "2K4k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with c8d8 shielding the king.", "Play c8d8 to begin the bridge.", listOf("c8d8", "h8g8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "8/1k6/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "b7c8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "b8c8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "8/6k1/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "g7h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "g8h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "h7h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/3K4/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d6e7 shielding the king.", "Play d6e7 to begin the bridge.", listOf("d6e7", "h8g8", "e7e8")),
        RawLesson("Lucena Bridge on B-file", "8/1k1K4/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "b7c8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "1k6/3K4/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "b8c8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "8/3K2k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "g7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "6k1/3K4/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "g8h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "8/3K3k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "h7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "7k/3K4/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d7e8 shielding the king.", "Play d7e8 to begin the bridge.", listOf("d7e8", "h8g8", "e8d8")),
        RawLesson("Lucena Bridge on B-file", "3K4/1k6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "b7c8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "1k1K4/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "b8c8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "3K4/6k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "g7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "3K2k1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "g8h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "3K4/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "h7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "3K3k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with d8e8 shielding the king.", "Play d8e8 to begin the bridge.", listOf("d8e8", "h8g8", "e8d8")),
        RawLesson("Lucena Bridge on B-file", "8/1k6/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6f7 shielding the king.", "Play e6f7 to begin the bridge.", listOf("e6f7", "b7c8", "f7g8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6f7 shielding the king.", "Play e6f7 to begin the bridge.", listOf("e6f7", "b8c8", "f7g8")),
        RawLesson("Lucena Bridge on B-file", "8/6k1/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6e7 shielding the king.", "Play e6e7 to begin the bridge.", listOf("e6e7", "g7h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6e7 shielding the king.", "Play e6e7 to begin the bridge.", listOf("e6e7", "g8h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6f7 shielding the king.", "Play e6f7 to begin the bridge.", listOf("e6f7", "h7h8", "f7f8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/4K3/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e6f7 shielding the king.", "Play e6f7 to begin the bridge.", listOf("e6f7", "h8h7", "f7f8")),
        RawLesson("Lucena Bridge on B-file", "8/1k2K3/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7f8 shielding the king.", "Play e7f8 to begin the bridge.", listOf("e7f8", "b7c8", "f8g8")),
        RawLesson("Lucena Bridge on B-file", "1k6/4K3/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7f8 shielding the king.", "Play e7f8 to begin the bridge.", listOf("e7f8", "b8c8", "f8g8")),
        RawLesson("Lucena Bridge on B-file", "8/4K1k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7e8 shielding the king.", "Play e7e8 to begin the bridge.", listOf("e7e8", "g7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "6k1/4K3/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7e8 shielding the king.", "Play e7e8 to begin the bridge.", listOf("e7e8", "g8h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "8/4K2k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7f8 shielding the king.", "Play e7f8 to begin the bridge.", listOf("e7f8", "h7h8", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "7k/4K3/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e7f8 shielding the king.", "Play e7f8 to begin the bridge.", listOf("e7f8", "h8h7", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "4K3/1k6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8f8 shielding the king.", "Play e8f8 to begin the bridge.", listOf("e8f8", "b7c8", "f8g8")),
        RawLesson("Lucena Bridge on B-file", "1k2K3/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8f8 shielding the king.", "Play e8f8 to begin the bridge.", listOf("e8f8", "b8c8", "f8g8")),
        RawLesson("Lucena Bridge on B-file", "4K3/6k1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8d8 shielding the king.", "Play e8d8 to begin the bridge.", listOf("e8d8", "g7h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "4K1k1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8d8 shielding the king.", "Play e8d8 to begin the bridge.", listOf("e8d8", "g8h8", "d8e8")),
        RawLesson("Lucena Bridge on B-file", "4K3/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8f8 shielding the king.", "Play e8f8 to begin the bridge.", listOf("e8f8", "h7h8", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "4K2k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with e8f8 shielding the king.", "Play e8f8 to begin the bridge.", listOf("e8f8", "h8h7", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "8/1k6/5K2/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f6g7 shielding the king.", "Play f6g7 to begin the bridge.", listOf("f6g7", "b7c8", "g7h8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/5K2/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f6g7 shielding the king.", "Play f6g7 to begin the bridge.", listOf("f6g7", "b8c8", "g7h8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/5K2/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f6e7 shielding the king.", "Play f6e7 to begin the bridge.", listOf("f6e7", "g8h8", "e7f8")),
        RawLesson("Lucena Bridge on B-file", "8/7k/5K2/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f6f7 shielding the king.", "Play f6f7 to begin the bridge.", listOf("f6f7", "h7h8", "f7f8")),
        RawLesson("Lucena Bridge on B-file", "7k/8/5K2/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f6f7 shielding the king.", "Play f6f7 to begin the bridge.", listOf("f6f7", "h8h7", "f7f8")),
        RawLesson("Lucena Bridge on B-file", "8/1k3K2/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f7g8 shielding the king.", "Play f7g8 to begin the bridge.", listOf("f7g8", "b7c8", "g8h8")),
        RawLesson("Lucena Bridge on B-file", "1k6/5K2/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f7g8 shielding the king.", "Play f7g8 to begin the bridge.", listOf("f7g8", "b8c8", "g8h8")),
        RawLesson("Lucena Bridge on B-file", "8/5K1k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f7f8 shielding the king.", "Play f7f8 to begin the bridge.", listOf("f7f8", "h7h8", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "7k/5K2/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f7f8 shielding the king.", "Play f7f8 to begin the bridge.", listOf("f7f8", "h8h7", "f8e8")),
        RawLesson("Lucena Bridge on B-file", "5K2/1k6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f8g8 shielding the king.", "Play f8g8 to begin the bridge.", listOf("f8g8", "b7c8", "g8h8")),
        RawLesson("Lucena Bridge on B-file", "1k3K2/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f8g8 shielding the king.", "Play f8g8 to begin the bridge.", listOf("f8g8", "b8c8", "g8h8")),
        RawLesson("Lucena Bridge on B-file", "5K2/7k/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f8e8 shielding the king.", "Play f8e8 to begin the bridge.", listOf("f8e8", "h7h8", "e8f8")),
        RawLesson("Lucena Bridge on B-file", "5K1k/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with f8e8 shielding the king.", "Play f8e8 to begin the bridge.", listOf("f8e8", "h8g8", "e8d8")),
        RawLesson("Lucena Bridge on B-file", "8/1k6/6K1/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g6h7 shielding the king.", "Play g6h7 to begin the bridge.", listOf("g6h7", "b7c8", "h7h8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/6K1/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g6h7 shielding the king.", "Play g6h7 to begin the bridge.", listOf("g6h7", "b8c8", "h7h8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/6K1/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g6h6 shielding the king.", "Play g6h6 to begin the bridge.", listOf("g6h6", "g8h8", "h6g6")),
        RawLesson("Lucena Bridge on B-file", "7k/8/6K1/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g6f7 shielding the king.", "Play g6f7 to begin the bridge.", listOf("g6f7", "h8h7", "f7f8")),
        RawLesson("Lucena Bridge on B-file", "8/1k4K1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g7h8 shielding the king.", "Play g7h8 to begin the bridge.", listOf("g7h8", "b7c8", "h8g8")),
        RawLesson("Lucena Bridge on B-file", "1k6/6K1/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g7h8 shielding the king.", "Play g7h8 to begin the bridge.", listOf("g7h8", "b8c8", "h8g8")),
        RawLesson("Lucena Bridge on B-file", "6K1/1k6/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g8h8 shielding the king.", "Play g8h8 to begin the bridge.", listOf("g8h8", "b7c8", "h8g8")),
        RawLesson("Lucena Bridge on B-file", "1k4K1/8/8/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with g8h8 shielding the king.", "Play g8h8 to begin the bridge.", listOf("g8h8", "b8c8", "h8g8")),
        RawLesson("Lucena Bridge on B-file", "8/1k6/7K/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with h6h7 shielding the king.", "Play h6h7 to begin the bridge.", listOf("h6h7", "b7c8", "h7h8")),
        RawLesson("Lucena Bridge on B-file", "1k6/8/7K/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with h6h7 shielding the king.", "Play h6h7 to begin the bridge.", listOf("h6h7", "b8c8", "h7h8")),
        RawLesson("Lucena Bridge on B-file", "6k1/8/7K/1P6/8/8/8/R7 w - - 0 1", "Execute the Lucena bridge maneuver with h6g6 shielding the king.", "Play h6g6 to begin the bridge.", listOf("h6g6", "g8h8", "g6f7"))
    )

    private var cachedLessons: List<EndgameLesson>? = null

    fun getLessons(): List<EndgameLesson> {
        cachedLessons?.let { return it }
        val lessons = (1..100).map { level ->
            val tier = when {
                level <= 35 -> "Easy"
                level <= 70 -> "Moderate"
                else -> "Hard"
            }
            val base = pool[level - 1]
            EndgameLesson(
                id = "e_lucena_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Lucena",
                fen = base.fen,
                explanation = "Level $level ($tier difficulty): ${base.explanation}",
                hint = base.hint,
                solutionMoves = base.moves
            )
        }
        cachedLessons = lessons
        return lessons
    }
}
