package com.chessmaster.play.model

enum class PieceSetId(val id: String, val displayName: String) {
    BURNETT("burnett", "Colin M.L. Burnett"),
    MERIDA("merida", "Merida"),
    PIROUETTI("pirouetti", "Pirouetti"),
    CHESSNUT("chessnut", "Chessnut"),
    CHESS7("chess7", "Chess7"),
    ALPHA("alpha", "Alpha"),
    REILLYCRAIG("reillycraig", "Reillycraig"),
    COMPANION("companion", "Companion"),
    RIOHACHA("riohacha", "Riohacha"),
    KOSAL("kosal", "Kosal"),
    MONARCHY("monarchy", "Monarchy"),
    COOKE("cooke", "Cooke"),
    SHAPES("shapes", "Shapes"),
    KIWEN_SUWI("kiwen_suwi", "Kiwen-suwi"),
    HORSEY("horsey", "Horsey"),
    ANARCANDY("anarcandy", "Anarcandy"),
    XKCD("xkcd", "xkcd"),
    LETTER("letter", "Letter"),
    DISGUISED("disguised", "Disguised"),
    SYMMETRIC("symmetric", "Symmetric"),
    NEO("neo", "Neo"),
    WOOD("wood", "Wood");

    companion object {
        val ALL_SETS = listOf(
            BURNETT,
            MERIDA,
            PIROUETTI,
            CHESSNUT,
            CHESS7,
            ALPHA,
            REILLYCRAIG,
            COMPANION,
            RIOHACHA,
            KOSAL,
            MONARCHY,
            COOKE,
            SHAPES,
            KIWEN_SUWI,
            HORSEY,
            ANARCANDY,
            XKCD,
            LETTER,
            DISGUISED,
            SYMMETRIC
        )

        fun fromId(id: String): PieceSetId {
            return entries.firstOrNull { it.id == id } ?: BURNETT
        }
    }
}
