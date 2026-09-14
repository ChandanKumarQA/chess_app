package com.chessmaster.play.model

enum class BotCategory(
    val displayName: String,
    val ratingRange: String,
    val minRating: Int,
    val maxRating: Int,
    val count: Int,
    val subtitle: String = ""
) {
    BAND_CLASS("Band Class", "300 - 1200", 300, 1200, 5, "Special Event"),
    BEGINNER("Beginner", "250 - 1000", 250, 1000, 13, "Fundamental Basics"),
    INTERMEDIATE("Intermediate", "1000 - 1500", 1000, 1500, 13, "Solid Competitors"),
    PRO("Pro", "1500 - 2000", 1500, 2000, 23, "Sharp Tacticians"),
    MASTER("Master", "2000 - 2400", 2000, 2400, 6, "Titled Champions"),
    GRANDMASTER("Grandmaster", "2400 - 2900+", 2400, 2900, 10, "World-Class Super GMs"),
    ADAPTIVE("Adaptive", "1200 - 2000", 1200, 2000, 5, "Dynamic Strength"),
    ATHLETES("Athletes", "1350 - 1850", 1350, 1850, 13, "Sports Icons");

    companion object {
        val NEW_TO_CHESS get() = BEGINNER
        val ADVANCED get() = PRO
    }
}

enum class HairType {
    SHORT,
    CURLY,
    AFRO,
    BUN,
    PONYTAIL,
    BEANIE,
    CAP,
    SLICK,
    MESSY,
    BALD
}

enum class FacialHairType {
    NONE,
    STUBBLE,
    FULL_BEARD,
    MUSTACHE,
    GOATEE
}

enum class AccessoryType {
    NONE,
    TRIANGLE,
    VIOLIN,
    DRUM,
    SAXOPHONE,
    BATON,
    HEADPHONES,
    JERSEY
}

data class BotAvatarStyle(
    val skinColor: Long = 0xFFFFDBAC,
    val hairStyle: HairType = HairType.SHORT,
    val hairColor: Long = 0xFF5A3825,
    val hasGlasses: Boolean = false,
    val glassesColor: Long = 0xFF333333,
    val facialHair: FacialHairType = FacialHairType.NONE,
    val facialHairColor: Long = hairColor,
    val shirtColor: Long = 0xFF43A047,
    val shirtSecondaryColor: Long? = null,
    val accessory: AccessoryType = AccessoryType.NONE
)

data class BotProfile(
    val id: String,
    val name: String,
    val title: String = "",
    val category: BotCategory,
    val rating: Int,
    val flag: String,
    val catchphrase: String,
    val avatarStyle: BotAvatarStyle,
    val coinCost: Int = 0,
    val isUnlockedByDefault: Boolean = false
) {
    val fullDisplayName: String
        get() = if (title.isNotEmpty()) "$name - $title" else name
}
