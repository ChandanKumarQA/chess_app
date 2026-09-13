package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object PhilidorDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5a6.", "Play b5a6.", listOf("b5a6", "b8c8", "a6a7")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5d5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4d5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4d5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5b6.", "Play b5b6.", listOf("b5b6", "b8c8", "b6a7")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5b6")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4c5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4c5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c5d5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5b5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4b5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4b5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c5b5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5d4")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4d4")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4d4")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c6d6")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5d6")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4d3")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4d3")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c6b6")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h7h8", "c5d6")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4c3")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h7h8", "c4d5")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c6d5")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "a8h8", "c5d6")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c4b3")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "a8h8", "c4d5")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "h8g8", "c6d7")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/1K6/1P6/8/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h8g8", "c5d6")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4d5")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/8/1P6/1K6/8/R7 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4c5")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5a6.", "Play b5a6.", listOf("b5a6", "b8c8", "a6b6")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5c4")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1b3")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4c3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5b6.", "Play b5b6.", listOf("b5b6", "b8c8", "b6c6")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "b1b3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1b2")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c4b3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c5d4")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "b1b2")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1h1")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "b1b3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c5c4")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "b1h1")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1g1")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "b1b2")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c6c5")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c5c6")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1f1")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "b1h1")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c6b5")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h7h8", "c5d5")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1e1")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h7h8", "c4c5")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "b1b3")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "a8h8", "c5d5")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "b1d1")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "a8h8", "c4c5")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "h8g8", "c6d6")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/1K6/1P6/8/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h8g8", "c5d5")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4b5")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/8/1P6/1K6/8/1R6 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4d4")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5a6.", "Play b5a6.", listOf("b5a6", "b8a8", "a6b6")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c1c4")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1c3")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c1c3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5b6.", "Play b5b6.", listOf("b5b6", "b8a8", "b6c7")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c1c3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/1r6/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1c2")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/1r6/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c1c2")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c1c4")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c1c2")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/6r1/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1h1")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/6r1/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c1h1")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b8c8", "c1c3")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c1h1")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/7r/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1g1")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/7r/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c1g1")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c1c5")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "c1g1")),
        RawLesson("Philidor Defense (B-file)", "1k6/r7/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1f1")),
        RawLesson("Philidor Defense (B-file)", "8/rk6/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "c1f1")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c1c4")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h7h8", "c5b5")),
        RawLesson("Philidor Defense (B-file)", "1k6/7r/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1e1")),
        RawLesson("Philidor Defense (B-file)", "8/1k5r/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h7h8", "c4b5")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "b8c8", "c1c3")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "a8h8", "c5b5")),
        RawLesson("Philidor Defense (B-file)", "rk6/8/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "c1d1")),
        RawLesson("Philidor Defense (B-file)", "r7/1k6/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "a8h8", "c4b5")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c6.", "Play b5c6.", listOf("b5c6", "h8g8", "c6b6")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/1K6/1P6/8/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "h8g8", "c5b5")),
        RawLesson("Philidor Defense (B-file)", "1k5r/8/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4d3")),
        RawLesson("Philidor Defense (B-file)", "7r/1k6/8/8/1P6/1K6/8/2R5 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "h8g8", "c4c3")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/1K6/1P6/8/8/3R4 w - - 0 1", "Challenge Black's 6th rank barrier with b5a6.", "Play b5a6.", listOf("b5a6", "b8c8", "a6b5")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/1K6/1P6/8/8/3R4 w - - 0 1", "Challenge Black's 6th rank barrier with b5c5.", "Play b5c5.", listOf("b5c5", "b7c8", "d1d8")),
        RawLesson("Philidor Defense (B-file)", "1k6/8/r7/8/1P6/1K6/8/3R4 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b8c8", "d1d8")),
        RawLesson("Philidor Defense (B-file)", "8/1k6/r7/8/1P6/1K6/8/3R4 w - - 0 1", "Challenge Black's 6th rank barrier with b3c4.", "Play b3c4.", listOf("b3c4", "b7c8", "d1d8"))
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
                id = "e_philidor_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Philidor",
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
