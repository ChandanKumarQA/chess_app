package com.chessmaster.play.data

import com.chessmaster.play.model.EndgameLesson

object BishopEndgameDatabase {

    private data class RawLesson(
        val subtitle: String,
        val fen: String,
        val explanation: String,
        val hint: String,
        val moves: List<String>
    )

    private val pool = listOf(
        RawLesson("Wrong Bishop & Rook Pawn", "7k/8/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Diagonal Domination", "8/8/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Bishop Central Blockade", "8/8/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Wing Domination", "8/8/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Long Diagonal Control", "8/8/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Opposite Colored Bishop Draw", "8/8/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 7)", "7k/8/8/7P/8/8/P4B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 8)", "7k/8/8/7P/8/8/1P3B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 9)", "7k/8/8/7P/8/8/2P2B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 10)", "7k/8/8/7P/8/8/5B1P/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 11)", "7k/p7/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 12)", "7k/1p6/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 13)", "7k/2p5/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 14)", "7k/6p1/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 15)", "7k/5p2/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 16)", "7k/8/8/7P/8/P7/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 17)", "7k/8/8/7P/8/7P/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 18)", "7k/8/p7/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Wrong Bishop & Rook Pawn (Var 19)", "7k/8/7p/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Diagonal Domination (Var 20)", "8/8/4k3/8/8/3B4/P3K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 21)", "8/8/4k3/8/8/3B4/1P2K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 22)", "8/8/4k3/8/8/3B4/2P1K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 23)", "8/8/4k3/8/8/3B4/4K2P/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 24)", "8/8/4k3/8/8/3B4/4K1P1/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 25)", "8/8/4k3/8/8/3B4/4KP2/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 26)", "8/p7/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 27)", "8/1p6/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 28)", "8/2p5/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 29)", "8/7p/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 30)", "8/6p1/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 31)", "8/5p2/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 32)", "8/8/4k3/8/8/P2B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 33)", "8/8/4k3/8/8/3B3P/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 34)", "8/8/p3k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Diagonal Domination (Var 35)", "8/8/4k2p/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Bishop Central Blockade (Var 36)", "8/8/4k3/8/8/2B5/P3K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 37)", "8/8/4k3/8/8/2B5/1P2K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 38)", "8/8/4k3/8/8/2B5/2P1K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 39)", "8/8/4k3/8/8/2B5/4K2P/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 40)", "8/8/4k3/8/8/2B5/4K1P1/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 41)", "8/8/4k3/8/8/2B5/4KP2/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 42)", "8/p7/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 43)", "8/1p6/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 44)", "8/2p5/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 45)", "8/7p/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 46)", "8/6p1/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 47)", "8/5p2/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 48)", "8/8/4k3/8/8/P1B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 49)", "8/8/4k3/8/8/2B4P/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 50)", "8/8/p3k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Central Blockade (Var 51)", "8/8/4k2p/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Wing Domination (Var 52)", "8/8/2k5/8/8/3B4/P1K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 53)", "8/8/2k5/8/8/3B4/1PK5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 54)", "8/8/2k5/8/8/3B4/2K4P/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 55)", "8/8/2k5/8/8/3B4/2K3P1/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 56)", "8/8/2k5/8/8/3B4/2K2P2/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 57)", "8/p7/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 58)", "8/1p6/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 59)", "8/2p5/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 60)", "8/7p/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 61)", "8/6p1/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 62)", "8/5p2/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 63)", "8/8/2k5/8/8/P2B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 64)", "8/8/2k5/8/8/3B3P/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 65)", "8/8/p1k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Wing Domination (Var 66)", "8/8/2k4p/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3")),
        RawLesson("Bishop Long Diagonal Control (Var 67)", "8/8/8/8/8/5B2/P3K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 68)", "8/8/8/8/8/5B2/1P2K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 69)", "8/8/8/8/8/5B2/2P1K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 70)", "8/8/8/8/8/5B2/4K1P1/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 71)", "8/8/8/8/8/5B2/4KP2/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 72)", "8/p7/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 73)", "8/1p6/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 74)", "8/2p5/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 75)", "8/7p/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 76)", "8/6p1/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 77)", "8/5p2/8/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 78)", "8/8/8/8/8/P4B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 79)", "8/8/8/8/8/5B1P/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 80)", "8/8/p7/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Bishop Long Diagonal Control (Var 81)", "8/8/7p/8/8/5B2/4K3/6k1 w - - 0 1", "Lock the opponent King in the corner with Bf3-e4! Prepare Ke2-f3.", "Play Be4 then Kf3.", listOf("f3e4", "g1h2", "e2f3")),
        RawLesson("Opposite Colored Bishop Draw (Var 82)", "8/8/4k3/8/3B4/8/P3K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 83)", "8/8/4k3/8/3B4/8/1P2K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 84)", "8/8/4k3/8/3B4/8/2P1K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 85)", "8/8/4k3/8/3B4/8/4K2P/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 86)", "8/8/4k3/8/3B4/8/4K1P1/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 87)", "8/p7/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 88)", "8/1p6/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 89)", "8/2p5/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 90)", "8/7p/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 91)", "8/6p1/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 92)", "8/5p2/4k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 93)", "8/8/4k3/8/3B4/P7/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 94)", "8/8/4k3/8/3B4/7P/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 95)", "8/8/p3k3/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Opposite Colored Bishop Draw (Var 96)", "8/8/4k2p/8/3B4/8/4K3/5b2 w - - 0 1", "Neutralize threats with Ke2xf1! White secures the drawn opposite bishop position.", "Take the bishop with Kxf1.", listOf("e2f1", "e6d5", "f1f2")),
        RawLesson("Wrong Bishop & Rook Pawn", "7k/8/8/7P/8/8/5B2/6K1 w - - 0 1", "A dark-squared bishop cannot control the light promotion square h8! Centralize Bf2-d4 and bring your King.", "Check with Bd4, then advance Kg2.", listOf("f2d4", "h8h7", "g1g2")),
        RawLesson("Diagonal Domination", "8/8/4k3/8/8/3B4/4K3/8 w - - 0 1", "Deliver check along the diagonal with Bd3-c4+! Control key squares while centralizing your King with Ke2-e3.", "Check with Bc4, then centralize Ke3.", listOf("d3c4", "e6e5", "e2e3")),
        RawLesson("Bishop Central Blockade", "8/8/4k3/8/8/2B5/4K3/8 w - - 0 1", "Blockade the central squares with Bc3-d4! Coordinate King and Bishop on dark squares.", "Centralize with Bd4, then Kd3.", listOf("c3d4", "e6d5", "e2d3")),
        RawLesson("Bishop Wing Domination", "8/8/2k5/8/8/3B4/2K5/8 w - - 0 1", "Deliver check with Bd3-c4+! Lock the Black King on the queenside.", "Play Bc4 then Kc3.", listOf("d3c4", "c6c5", "c2c3"))
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
                id = "e_bishop_endgame_$level",
                title = "Level $level ($tier difficulty) • ${base.subtitle}",
                category = "Bishop Endgame",
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
