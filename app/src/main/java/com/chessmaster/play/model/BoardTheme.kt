package com.chessmaster.play.model

import androidx.compose.ui.graphics.Color

data class BoardTheme(
    val id: String,
    val name: String,
    val lightColor: Color,
    val darkColor: Color
)

object BoardThemes {
    val MAPLE_2 = BoardTheme("maple_2", "Maple 2", Color(0xFFE2B67C), Color(0xFFAC6834))
    val BLUE = BoardTheme("blue", "Blue", Color(0xFFDEE3E6), Color(0xFF8CA2AD))
    val BLUE_2 = BoardTheme("blue_2", "Blue 2", Color(0xFFD4DFE8), Color(0xFF6B89A3))
    val BLUE_3 = BoardTheme("blue_3", "Blue 3", Color(0xFFCBE0F5), Color(0xFF4682B4))
    val BLUE_MARBLE = BoardTheme("blue_marble", "Blue Marble", Color(0xFFE2EAF2), Color(0xFF5A7F9D))
    val CANVAS = BoardTheme("canvas", "Canvas", Color(0xFFE8EBF0), Color(0xFF7B8B9E))
    val LEATHER = BoardTheme("leather", "Leather", Color(0xFFEBDABF), Color(0xFFC48E38))
    val IC = BoardTheme("ic", "IC", Color(0xFFECECD8), Color(0xFFC3C695))
    val GREEN = BoardTheme("green", "Green", Color(0xFFEEEED2), Color(0xFF769656))
    val MARBLE = BoardTheme("marble", "Marble", Color(0xFFA8B8A0), Color(0xFF5A7359))
    val GREEN_PLASTIC = BoardTheme("green_plastic", "Green Plastic", Color(0xFFF4F8B8), Color(0xFF528D58))
    val GREY = BoardTheme("grey", "Grey", Color(0xFFD8D8D8), Color(0xFF828282))
    val METAL = BoardTheme("metal", "Metal", Color(0xFFDCDCDC), Color(0xFF8C9298))
    val OLIVE = BoardTheme("olive", "Olive", Color(0xFFCBCBA6), Color(0xFF8A8A68))
    val NEWSPAPER = BoardTheme("newspaper", "Newspaper", Color(0xFFECECEC), Color(0xFFB4B4B4))
    val PURPLE = BoardTheme("purple", "Purple", Color(0xFFB8A5D6), Color(0xFF7B529E))
    val PURPLE_DIAG = BoardTheme("purple_diag", "Purple-Diag", Color(0xFFDFD6F0), Color(0xFF9478B8))
    val PINK = BoardTheme("pink", "Pink", Color(0xFFFFF5C0), Color(0xFFF06575))
    val HORSEY = BoardTheme("horsey", "Horsey", Color(0xFFF5E0C2), Color(0xFFA67650))
    val WALNUT = BoardTheme("walnut", "Walnut", Color(0xFFE4D5B7), Color(0xFF8B5A2B))
    val CAPPUCCINO = BoardTheme("cappuccino", "Cappuccino", Color(0xFFF0D9B5), Color(0xFFB58863))

    val ALL_THEMES: List<BoardTheme> = listOf(
        MAPLE_2,
        BLUE,
        BLUE_2,
        BLUE_3,
        BLUE_MARBLE,
        CANVAS,
        LEATHER,
        IC,
        GREEN,
        MARBLE,
        GREEN_PLASTIC,
        GREY,
        METAL,
        OLIVE,
        NEWSPAPER,
        PURPLE,
        PURPLE_DIAG,
        PINK,
        HORSEY,
        WALNUT,
        CAPPUCCINO
    )

    fun getById(id: String): BoardTheme {
        return ALL_THEMES.firstOrNull { it.id == id } ?: GREEN
    }
}
