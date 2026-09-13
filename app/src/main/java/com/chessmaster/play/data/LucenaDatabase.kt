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
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file) (Var 6)", "4K3/4P3/4k3/8/8/8/rP6/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 7)", "4K3/4P3/4k3/8/8/8/r1P5/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 8)", "4K3/4P3/4k3/8/8/8/r6P/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 9)", "4K3/4P3/4k3/8/8/8/r5P1/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 10)", "4K3/4P3/4k3/8/8/8/r4P2/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 11)", "4K3/p3P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 12)", "4K3/1p2P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 13)", "4K3/2p1P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 14)", "4K3/4P2p/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 15)", "4K3/4P1p1/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 16)", "4K3/4Pp2/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 17)", "4K3/4P3/4k3/8/8/P7/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 18)", "4K3/4P3/4k3/8/8/7P/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 19)", "4K3/4P3/p3k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (e-file) (Var 20)", "4K3/4P3/4k2p/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file) (Var 21)", "3K4/3P4/3k4/8/8/8/rP6/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 22)", "3K4/3P4/3k4/8/8/8/r1P5/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 23)", "3K4/3P4/3k4/8/8/8/r6P/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 24)", "3K4/3P4/3k4/8/8/8/r5P1/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 25)", "3K4/3P4/3k4/8/8/8/r4P2/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 26)", "3K4/p2P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 27)", "3K4/1p1P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 28)", "3K4/2pP4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 29)", "3K4/3P3p/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 30)", "3K4/3P2p1/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 31)", "3K4/3P1p2/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 32)", "3K4/3P4/3k4/8/8/P7/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 33)", "3K4/3P4/3k4/8/8/7P/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 34)", "3K4/3P4/p2k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (d-file) (Var 35)", "3K4/3P4/3k3p/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file) (Var 36)", "2K5/2P5/2k5/8/8/8/rP6/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 37)", "2K5/2P5/2k5/8/8/8/r6P/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 38)", "2K5/2P5/2k5/8/8/8/r5P1/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 39)", "2K5/2P5/2k5/8/8/8/r4P2/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 40)", "2K5/p1P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 41)", "2K5/1pP5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 42)", "2K5/2P4p/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 43)", "2K5/2P3p1/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 44)", "2K5/2P2p2/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 45)", "2K5/2P5/2k5/8/8/P7/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 46)", "2K5/2P5/2k5/8/8/7P/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 47)", "2K5/2P5/p1k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (c-file) (Var 48)", "2K5/2P5/2k4p/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file) (Var 49)", "5K2/5P2/5k2/8/8/8/rP6/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 50)", "5K2/5P2/5k2/8/8/8/r1P5/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 51)", "5K2/5P2/5k2/8/8/8/r6P/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 52)", "5K2/5P2/5k2/8/8/8/r5P1/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 53)", "5K2/p4P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 54)", "5K2/1p3P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 55)", "5K2/2p2P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 56)", "5K2/5P1p/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 57)", "5K2/5Pp1/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 58)", "5K2/5P2/5k2/8/8/P7/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 59)", "5K2/5P2/5k2/8/8/7P/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 60)", "5K2/5P2/p4k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (f-file) (Var 61)", "5K2/5P2/5k1p/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file) (Var 62)", "1K6/1P6/1k6/8/8/8/r1P5/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 63)", "1K6/1P6/1k6/8/8/8/r6P/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 64)", "1K6/1P6/1k6/8/8/8/r5P1/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 65)", "1K6/1P6/1k6/8/8/8/r4P2/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 66)", "1K6/pP6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 67)", "1K6/1Pp5/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 68)", "1K6/1P5p/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 69)", "1K6/1P4p1/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 70)", "1K6/1P3p2/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 71)", "1K6/1P6/1k6/8/8/P7/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 72)", "1K6/1P6/1k6/8/8/7P/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 73)", "1K6/1P6/pk6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (b-file) (Var 74)", "1K6/1P6/1k5p/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4")),
        RawLesson("Lucena Bridge (d-file)", "3K4/3P4/3k4/8/8/8/r7/4R3 w - - 0 1", "Drive the defending King off the d-file with Re1-d1+, then place your Rook on the 4th rank with Rd1-d4 to shield future checks.", "Check on d1, then lift to d4.", listOf("e1d1", "d6c6", "d1d4")),
        RawLesson("Lucena Bridge (c-file)", "2K5/2P5/2k5/8/8/8/r7/3R4 w - - 0 1", "Building the bridge on the c-file: drive the Black King with Rd1-c1+, then drop back with Rc1-c4.", "Check on c1, then lift to c4.", listOf("d1c1", "c6d6", "c1c4")),
        RawLesson("Lucena Bridge (f-file)", "5K2/5P2/5k2/8/8/8/r7/6R1 w - - 0 1", "Execute the Lucena maneuver on the f-file: deliver check with Rg1-f1+ and prepare the bridge with Rf1-f4.", "Check on f1, then prepare Rf4.", listOf("g1f1", "f6e6", "f1f4")),
        RawLesson("Lucena Bridge (b-file)", "1K6/1P6/1k6/8/8/8/r7/2R5 w - - 0 1", "Building the bridge on the b-file: check with Rc1-b1+, then lift to Rb4.", "Check on b1, then lift to b4.", listOf("c1b1", "b6c6", "b1b4")),
        RawLesson("Lucena Bridge (e-file)", "4K3/4P3/4k3/8/8/8/r7/5R2 w - - 0 1", "The fundamental Lucena winning technique! Check Black's King with Rf1-e1 to drive him away, then lift your Rook with Re1-e4 to build the bridge!", "Play Rf1-e1+ then Re4.", listOf("f1e1", "e6d6", "e1e4"))
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
            val base = pool[(level - 1) % pool.size]
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
