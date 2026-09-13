package com.chessmaster.play.model

enum class BotCategory(
    val displayName: String,
    val count: Int,
    val subtitle: String = ""
) {
    BAND_CLASS("Band Class", 5, "Special Event"),
    NEW_TO_CHESS("New to Chess", 5, "Getting Started"),
    BEGINNER("Beginner", 15, "Friendly Opponents"),
    INTERMEDIATE("Intermediate", 15, "Solid Competitors"),
    ADVANCED("Advanced", 20, "Formidable Tacticians"),
    MASTER("Master", 10, "World-Class Grandmasters"),
    ADAPTIVE("Adaptive", 5, "Dynamic Strength"),
    ATHLETES("Athletes", 13, "Sports Icons")
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
