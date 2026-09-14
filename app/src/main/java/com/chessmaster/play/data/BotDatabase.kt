package com.chessmaster.play.data

import com.chessmaster.play.model.*

object BotDatabase {

    val allBots: List<BotProfile> by lazy {
        bandBots + beginnerBots + intermediateBots + proBots + masterBots + grandmasterBots + adaptiveBots + athleteBots
    }

    // Backwards compatibility aliases
    val newToChessBots: List<BotProfile> get() = beginnerBots.take(5)
    val advancedBots: List<BotProfile> get() = proBots

    // ==========================================
    // 1. BAND CLASS (5 Bots • Rating 300 - 1200)
    // ==========================================
    val bandBots = listOf(
        BotProfile(
            id = "band_cliff",
            name = "Cliff",
            title = "Triangle",
            category = BotCategory.BAND_CLASS,
            rating = 300,
            flag = "🇩🇪",
            catchphrase = "Ding! Ding! Ding! Time for a game!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFDBAC,
                hairStyle = HairType.SHORT,
                hairColor = 0xFF8D5524,
                hasGlasses = true,
                glassesColor = 0xFF37474F,
                shirtColor = 0xFF388E3C,
                accessory = AccessoryType.TRIANGLE
            ),
            coinCost = 5,
            isUnlockedByDefault = true
        ),
        BotProfile(
            id = "band_leo",
            name = "Leo",
            title = "Violin",
            category = BotCategory.BAND_CLASS,
            rating = 500,
            flag = "🇺🇸",
            catchphrase = "Let's play in perfect harmony on the 64 squares!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFF8D5524,
                hairStyle = HairType.SHORT,
                hairColor = 0xFF212121,
                shirtColor = 0xFF1B5E20,
                accessory = AccessoryType.VIOLIN
            ),
            coinCost = 8,
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "band_nora",
            name = "Nora",
            title = "Drums",
            category = BotCategory.BAND_CLASS,
            rating = 700,
            flag = "🇬🇧",
            catchphrase = "Get ready for a fast, rhythmic tempo!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFE0BD,
                hairStyle = HairType.MESSY,
                hairColor = 0xFFE91E63,
                shirtColor = 0xFF212121,
                accessory = AccessoryType.DRUM
            ),
            coinCost = 12,
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "band_zara",
            name = "Zara",
            title = "Saxophone",
            category = BotCategory.BAND_CLASS,
            rating = 900,
            flag = "🇫🇷",
            catchphrase = "Smooth jazz, smooth knight maneuvers.",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFF704126,
                hairStyle = HairType.AFRO,
                hairColor = 0xFF1C1C1C,
                hasGlasses = true,
                glassesColor = 0xFFE91E63,
                shirtColor = 0xFF263238,
                accessory = AccessoryType.SAXOPHONE
            ),
            coinCost = 20,
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "band_maestro",
            name = "Maestro",
            title = "Conductor",
            category = BotCategory.BAND_CLASS,
            rating = 1200,
            flag = "🇦🇹",
            catchphrase = "Every piece in the orchestra has its grand purpose.",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFE0BD,
                hairStyle = HairType.BALD,
                hairColor = 0xFFCCCCCC,
                hasGlasses = true,
                facialHair = FacialHairType.FULL_BEARD,
                facialHairColor = 0xFFCCCCCC,
                shirtColor = 0xFF8D6E63,
                accessory = AccessoryType.BATON
            ),
            coinCost = 30,
            isUnlockedByDefault = false
        )
    )

    // ==========================================
    // 2. BEGINNER (13 Bots • Rating 250 - 1000)
    // ==========================================
    val beginnerBots = listOf(
        BotProfile(
            id = "new_martin",
            name = "Martin",
            category = BotCategory.BEGINNER,
            rating = 250,
            flag = "🇺🇸",
            catchphrase = "I love chess, even if I lose my Queen sometimes!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFDBAC,
                hairStyle = HairType.SHORT,
                hairColor = 0xFFC62828,
                shirtColor = 0xFFFF7043
            ),
            isUnlockedByDefault = true
        ),
        BotProfile(
            id = "new_elani",
            name = "Elani",
            category = BotCategory.BEGINNER,
            rating = 400,
            flag = "🇪🇸",
            catchphrase = "Pawns are cute! Let's play a friendly game.",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFE0BD,
                hairStyle = HairType.PONYTAIL,
                hairColor = 0xFF212121,
                shirtColor = 0xFF64B5F6
            ),
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "new_olivia",
            name = "Olivia",
            category = BotCategory.BEGINNER,
            rating = 550,
            flag = "🇨🇦",
            catchphrase = "Every grandmaster was once a beginner!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFE0BD,
                hairStyle = HairType.BUN,
                hairColor = 0xFF8D6E63,
                shirtColor = 0xFF37474F
            ),
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "new_mina",
            name = "Mina",
            category = BotCategory.BEGINNER,
            rating = 600,
            flag = "🇮🇳",
            catchphrase = "Chess is like a puzzle that changes every turn.",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFF8D5524,
                hairStyle = HairType.CURLY,
                hairColor = 0xFF212121,
                hasGlasses = true,
                shirtColor = 0xFF4A148C
            ),
            isUnlockedByDefault = false
        ),
        BotProfile(
            id = "new_aron",
            name = "Aron",
            category = BotCategory.BEGINNER,
            rating = 700,
            flag = "🇳🇱",
            catchphrase = "I'm practicing castling. Keep your king safe!",
            avatarStyle = BotAvatarStyle(
                skinColor = 0xFFFFDBAC,
                hairStyle = HairType.SHORT,
                hairColor = 0xFF5D4037,
                facialHair = FacialHairType.FULL_BEARD,
                shirtColor = 0xFFD84315
            ),
            isUnlockedByDefault = false
        ),
        BotProfile("beg_taro", "Taro", "", BotCategory.BEGINNER, 800, "🇯🇵", "Focus and patience bring victory.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.PONYTAIL, hairColor = 0xFF263238, shirtColor = 0xFFECEFF1), isUnlockedByDefault = false),
        BotProfile("beg_chloe", "Chloe", "", BotCategory.BEGINNER, 850, "🇫🇷", "En garde! A stylish opening awaits.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.BUN, hairColor = 0xFF5D4037, shirtColor = 0xFF1E88E5), isUnlockedByDefault = false),
        BotProfile("beg_fabian", "Fabian", "", BotCategory.BEGINNER, 900, "🇩🇪", "Structure your pieces cleanly.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, shirtColor = 0xFF8D6E63), isUnlockedByDefault = false),
        BotProfile("beg_liam", "Liam", "", BotCategory.BEGINNER, 950, "🇮🇪", "Luck of the Irish on my side!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.CURLY, hairColor = 0xFFBF360C, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF00695C), isUnlockedByDefault = false),
        BotProfile("beg_mateo", "Mateo", "", BotCategory.BEGINNER, 950, "🇲🇽", "Quick tactical strikes are fun.", BotAvatarStyle(skinColor = 0xFFD7CCC8, hairStyle = HairType.SHORT, hairColor = 0xFF424242, shirtColor = 0xFF00796B), isUnlockedByDefault = false),
        BotProfile("beg_viktor", "Viktor", "", BotCategory.BEGINNER, 1000, "🇷🇺", "A knight on the rim is dim.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF616161, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFBCAAA4), isUnlockedByDefault = false),
        BotProfile("beg_zoe", "Zoe", "", BotCategory.BEGINNER, 1000, "🇬🇧", "Check your diagonals before you move!", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF8E24AA, shirtColor = 0xFF33691E), isUnlockedByDefault = false),
        BotProfile("beg_amir", "Amir", "", BotCategory.BEGINNER, 1000, "🇪🇬", "The center belongs to the brave.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF3F51B5), isUnlockedByDefault = false)
    )

    // ==========================================
    // 3. INTERMEDIATE (13 Bots • Rating 1000 - 1500)
    // ==========================================
    val intermediateBots = listOf(
        BotProfile("beg_marcus", "Marcus", "", BotCategory.INTERMEDIATE, 1050, "🇮🇹", "Fianchetto bishops are my favorite.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.MESSY, hairColor = 0xFFBF360C, shirtColor = 0xFF00897B), isUnlockedByDefault = true),
        BotProfile("beg_samira", "Samira", "", BotCategory.INTERMEDIATE, 1050, "🇹🇷", "Never underestimate a passed pawn.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.BUN, hairColor = 0xFF212121, shirtColor = 0xFF6D4C41), isUnlockedByDefault = false),
        BotProfile("beg_sven", "Sven", "", BotCategory.INTERMEDIATE, 1100, "🇸🇪", "Let's see your Scandinavian defense!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF212121, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF4E342E), isUnlockedByDefault = false),
        BotProfile("beg_hans", "Hans", "", BotCategory.INTERMEDIATE, 1100, "🇦🇹", "Precision in every placement.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, facialHair = FacialHairType.MUSTACHE, shirtColor = 0xFF546E7A), isUnlockedByDefault = false),
        BotProfile("beg_elena", "Elena", "", BotCategory.INTERMEDIATE, 1150, "🇬🇷", "Pawn structure determines the endgame.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.AFRO, hairColor = 0xFF212121, shirtColor = 0xFFE91E63), isUnlockedByDefault = false),
        BotProfile("beg_devon", "Devon", "", BotCategory.INTERMEDIATE, 1200, "🇦🇺", "G'day! Ready to duel on the board?", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF6D4C41, shirtColor = 0xFF2E7D32), isUnlockedByDefault = false),
        BotProfile("beg_nelson", "Nelson", "", BotCategory.INTERMEDIATE, 1300, "🇺🇸", "My Queen will strike early and often!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFFFFA000, shirtColor = 0xFF7B1FA2), isUnlockedByDefault = false),
        BotProfile("int_david", "David", "", BotCategory.INTERMEDIATE, 1400, "🇬🇧", "Solid positional play wins over time.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.BEANIE, hairColor = 0xFF795548, shirtColor = 0xFF00695C), isUnlockedByDefault = false),
        BotProfile("int_maya", "Maya", "", BotCategory.INTERMEDIATE, 1400, "🇮🇳", "Knights jump where least expected.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF546E7A), isUnlockedByDefault = false),
        BotProfile("int_lucas", "Lucas", "", BotCategory.INTERMEDIATE, 1450, "🇧🇷", "Dynamic sacrifices bring dynamic wins.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF5D4037, shirtColor = 0xFF6A1B9A), isUnlockedByDefault = false),
        BotProfile("int_wendy", "Wendy", "", BotCategory.INTERMEDIATE, 1500, "🇺🇸", "I punish careless tactical blunders!", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.PONYTAIL, hairColor = 0xFF4E342E, shirtColor = 0xFFC2185B), isUnlockedByDefault = false),
        BotProfile("int_antonio", "Antonio", "", BotCategory.INTERMEDIATE, 1500, "🇮🇹", "Tactical forks are my signature move.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, shirtColor = 0xFF1565C0), isUnlockedByDefault = false),
        BotProfile("int_hassan", "Hassan", "", BotCategory.INTERMEDIATE, 1500, "🇲🇦", "The desert storm sweeps across the rank.", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFD7CCC8), isUnlockedByDefault = false)
    )

    // ==========================================
    // 4. PRO (23 Bots • Rating 1500 - 2000)
    // ==========================================
    val proBots = listOf(
        BotProfile("int_kareem", "Kareem", "", BotCategory.PRO, 1550, "🇳🇬", "A happy king is a well-defended king.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF8D6E63), isUnlockedByDefault = true),
        BotProfile("int_laura", "Laura", "", BotCategory.PRO, 1550, "🇩🇪", "Efficiency in pawn promotion.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.PONYTAIL, hairColor = 0xFF263238, shirtColor = 0xFF00897B), isUnlockedByDefault = false),
        BotProfile("int_fatima", "Fatima", "", BotCategory.PRO, 1600, "🇦🇪", "Control the open files with rooks.", BotAvatarStyle(skinColor = 0xFFD7CCC8, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF00796B), isUnlockedByDefault = false),
        BotProfile("int_chen", "Chen", "", BotCategory.PRO, 1600, "🇨🇳", "Patience will outlast haste.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF00838F), isUnlockedByDefault = false),
        BotProfile("int_isabel", "Isabel", "", BotCategory.PRO, 1600, "🇪🇸", "A sharp eye wins the day!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF263238, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFFAFAFA), isUnlockedByDefault = false),
        BotProfile("int_nathan", "Nathan", "", BotCategory.PRO, 1650, "🇨🇦", "Calculated risks yield the highest reward.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF37474F, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFECEFF1), isUnlockedByDefault = false),
        BotProfile("int_omar", "Omar", "", BotCategory.PRO, 1650, "🇪🇬", "Control the central outposts.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF00695C), isUnlockedByDefault = false),
        BotProfile("int_kenji", "Kenji", "", BotCategory.PRO, 1700, "🇯🇵", "Precision calculation down to the endgame.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFAD1457), isUnlockedByDefault = false),
        BotProfile("int_alina", "Alina", "", BotCategory.PRO, 1700, "🇺🇦", "Counterattacks are deadliest when unexpected.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF4E342E, shirtColor = 0xFF6D4C41), isUnlockedByDefault = false),
        BotProfile("adv_nadia", "Nadia", "", BotCategory.PRO, 1800, "🇷🇴", "Fast tactical swindles.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.CURLY, hairColor = 0xFF4E342E, shirtColor = 0xFFE91E63), isUnlockedByDefault = false),
        BotProfile("adv_artem", "Artem", "", BotCategory.PRO, 1850, "🇷🇺", "Deep calculation into queenless endgames.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.BEANIE, hairColor = 0xFF795548, shirtColor = 0xFFC2185B), isUnlockedByDefault = false),
        BotProfile("adv_tariq", "Tariq", "", BotCategory.PRO, 1850, "🇲🇦", "Flank attacks shatter stubborn defenses.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.AFRO, hairColor = 0xFF212121, shirtColor = 0xFF6D4C41), isUnlockedByDefault = false),
        BotProfile("adv_otto", "Otto", "", BotCategory.PRO, 1850, "🇩🇪", "Steel pawn shields.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF424242, shirtColor = 0xFF3F51B5), isUnlockedByDefault = false),
        BotProfile("adv_pierre", "Pierre", "", BotCategory.PRO, 1900, "🇫🇷", "French defense, iron counterplay.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF8D5524, shirtColor = 0xFFD7CCC8), isUnlockedByDefault = false),
        BotProfile("adv_felix", "Felix", "", BotCategory.PRO, 1900, "🇦🇹", "Every square counts.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFFBF360C, shirtColor = 0xFF37474F), isUnlockedByDefault = false),
        BotProfile("adv_dmitri", "Dmitri", "", BotCategory.PRO, 1900, "🇺🇦", "Rook and pawn endgames are won by millimeters.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, shirtColor = 0xFF00796B), isUnlockedByDefault = false),
        BotProfile("adv_marta", "Marta", "", BotCategory.PRO, 1900, "🇵🇱", "Nimzo-Indian mastery.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.PONYTAIL, hairColor = 0xFF795548, shirtColor = 0xFF2E7D32), isUnlockedByDefault = false),
        BotProfile("adv_stefan", "Stefan", "", BotCategory.PRO, 1950, "🇩🇪", "Positional strangulation.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, shirtColor = 0xFF00838F), isUnlockedByDefault = false),
        BotProfile("adv_tor", "Tor", "", BotCategory.PRO, 1950, "🇩🇰", "Patience of a glacier.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFFD84315, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFFAFAFA), isUnlockedByDefault = false),
        BotProfile("adv_raheem", "Raheem", "", BotCategory.PRO, 1950, "🇪🇬", "Dynamic knights dominate central squares.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF546E7A), isUnlockedByDefault = false),
        BotProfile("adv_li", "Li", "", BotCategory.PRO, 2000, "🇨🇳", "My endgame technique is deeply studied.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.PONYTAIL, hairColor = 0xFF8D5524, shirtColor = 0xFF1976D2), isUnlockedByDefault = false),
        BotProfile("adv_gabriel", "Gabriel", "", BotCategory.PRO, 2000, "🇧🇷", "Samba on the board with active pieces.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF795548, facialHair = FacialHairType.MUSTACHE, shirtColor = 0xFF8D6E63), isUnlockedByDefault = false),
        BotProfile("adv_simon", "Simon", "", BotCategory.PRO, 2000, "🇨🇭", "Neutral play until your defense cracks.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF616161, shirtColor = 0xFFC2185B), isUnlockedByDefault = false)
    )

    // ==========================================
    // 5. MASTER (6 Bots • Rating 2000 - 2400)
    // ==========================================
    val masterBots = listOf(
        BotProfile("adv_bjorn", "Bjorn", "", BotCategory.MASTER, 2050, "🇳🇴", "Cold logic, relentless pressure.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF4E342E, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFECEFF1), isUnlockedByDefault = true),
        BotProfile("adv_yuki", "Yuki", "", BotCategory.MASTER, 2050, "🇯🇵", "Swift harmony in piece coordination.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFFBF360C, shirtColor = 0xFF424242), isUnlockedByDefault = false),
        BotProfile("adv_vikram", "Vikram", "", BotCategory.MASTER, 2100, "🇮🇳", "Vishy inspired my sharp Kings Indian lines.", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF00897B), isUnlockedByDefault = false),
        BotProfile("adv_clara", "Clara", "", BotCategory.MASTER, 2100, "🇬🇧", "Queen sacrifices when least anticipated.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF5D4037, hasGlasses = true, shirtColor = 0xFF37474F), isUnlockedByDefault = false),
        BotProfile("adv_mateus", "Mateus", "", BotCategory.MASTER, 2150, "🇵🇹", "Grandmaster level opening prep.", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFBCAAA4), isUnlockedByDefault = false),
        BotProfile("adv_valeria", "Valeria", "", BotCategory.MASTER, 2200, "🇷🇺", "Former candidate master ready for battle.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.BUN, hairColor = 0xFF212121, shirtColor = 0xFF4A148C), isUnlockedByDefault = false)
    )

    // ==========================================
    // 6. GRANDMASTER (10 Bots • Rating 2400 - 2900+)
    // ==========================================
    val grandmasterBots = listOf(
        BotProfile("mas_levon", "Levon", "Grandmaster", BotCategory.GRANDMASTER, 2740, "🇦🇲", "Creative, artistic chess full of surprises.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.CURLY, hairColor = 0xFF5D4037, hasGlasses = true, shirtColor = 0xFF00897B), isUnlockedByDefault = true),
        BotProfile("mas_vishy", "Vishy", "Legend", BotCategory.GRANDMASTER, 2750, "🇮🇳", "The Tiger from Madras strikes lightning fast.", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.SHORT, hairColor = 0xFF424242, hasGlasses = true, shirtColor = 0xFF37474F), isUnlockedByDefault = false),
        BotProfile("mas_wesley", "Wesley", "Grandmaster", BotCategory.GRANDMASTER, 2750, "🇺🇸", "Patience and accuracy in every single phase.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF3E2723), isUnlockedByDefault = false),
        BotProfile("mas_anish", "Anish", "Grandmaster", BotCategory.GRANDMASTER, 2760, "🇳🇱", "Solid as a rock, draw is acceptable but winning is better.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF37474F, shirtColor = 0xFF00796B), isUnlockedByDefault = false),
        BotProfile("mas_maxime", "Maxime", "Grandmaster", BotCategory.GRANDMASTER, 2760, "🇫🇷", "Najdorf and Grünfeld master.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF616161, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF455A64), isUnlockedByDefault = false),
        BotProfile("mas_alireza", "Alireza", "Grandmaster", BotCategory.GRANDMASTER, 2770, "🇫🇷", "Speed, fire, and tactical aggression!", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFD32F2F), isUnlockedByDefault = false),
        BotProfile("mas_ding", "Ding", "World Champion", BotCategory.GRANDMASTER, 2780, "🇨🇳", "Calm and resolute in the most complex lines.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFECEFF1), isUnlockedByDefault = false),
        BotProfile("mas_fabiano", "Fabiano", "Super GM", BotCategory.GRANDMASTER, 2800, "🇺🇸", "My opening preparation goes 30 moves deep.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF5D4037, hasGlasses = true, shirtColor = 0xFF6A1B9A), isUnlockedByDefault = false),
        BotProfile("mas_hikaru", "Hikaru", "Super GM", BotCategory.GRANDMASTER, 2820, "🇺🇸", "Literally whatever you play, I've seen it before!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF212121, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF263238), isUnlockedByDefault = false),
        BotProfile("mas_magnus", "Magnus", "World Champion", BotCategory.GRANDMASTER, 2850, "🇳🇴", "I play any position until my opponent makes a mistake.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF6D4C41, shirtColor = 0xFF1565C0), isUnlockedByDefault = false)
    )

    // ==========================================
    // 7. ADAPTIVE (5 Bots • Dynamic Rating 1200 - 2000)
    // ==========================================
    val adaptiveBots = listOf(
        BotProfile("adp_jake", "Jake", "Adaptive", BotCategory.ADAPTIVE, 1200, "🇺🇸", "I adjust my playing strength to match your level!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF5D4037, shirtColor = 0xFF00838F), isUnlockedByDefault = true),
        BotProfile("adp_maya", "Maya", "Adaptive", BotCategory.ADAPTIVE, 1400, "🇧🇷", "The closer we play, the better the game gets.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.PONYTAIL, hairColor = 0xFF212121, shirtColor = 0xFF546E7A), isUnlockedByDefault = false),
        BotProfile("adp_sam", "Sam", "Adaptive", BotCategory.ADAPTIVE, 1600, "🇬🇧", "Always giving you just enough of a challenge.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF8D6E63, shirtColor = 0xFF00897B), isUnlockedByDefault = false),
        BotProfile("adp_troy", "Troy", "Adaptive", BotCategory.ADAPTIVE, 1800, "🇨🇦", "I learn your favorite openings as we play.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF2E7D32), isUnlockedByDefault = false),
        BotProfile("adp_elena", "Elena", "Adaptive", BotCategory.ADAPTIVE, 2000, "🇷🇺", "Adapting dynamically from beginner to master.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF6D4C41, shirtColor = 0xFFC2185B), isUnlockedByDefault = false)
    )

    // ==========================================
    // 8. ATHLETES (13 Bots • Rating 1350 - 1850)
    // ==========================================
    val athleteBots = listOf(
        BotProfile("ath_alvin", "Alvin", "Running Back", BotCategory.ATHLETES, 1350, "🇺🇸", "Elusive footwork like a nimble knight.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF212121, accessory = AccessoryType.JERSEY), isUnlockedByDefault = true),
        BotProfile("ath_serena", "Serena", "Tennis Icon", BotCategory.ATHLETES, 1400, "🇺🇸", "Ace every serve, strike every diagonal!", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.CURLY, hairColor = 0xFF8D6E63, shirtColor = 0xFFFAFAFA, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_bryce", "Bryce", "Baseball", BotCategory.ATHLETES, 1450, "🇺🇸", "Swinging for the fences on move 40!", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.CAP, hairColor = 0xFF424242, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFFD32F2F, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_erling", "Erling", "Striker", BotCategory.ATHLETES, 1500, "🇳🇴", "Precision striking inside the penalty box.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.PONYTAIL, hairColor = 0xFFFFB74D, shirtColor = 0xFF00E676, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_lamar", "Lamar", "Quarterback", BotCategory.ATHLETES, 1500, "🇺🇸", "Scrambling out of check into victory.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF4A148C, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_christian", "Christian", "Footballer", BotCategory.ATHLETES, 1550, "🇺🇸", "Dribbling through tight defenses.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF5D4037, facialHair = FacialHairType.STUBBLE, shirtColor = 0xFFD32F2F, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_derrick", "Derrick", "Basketball", BotCategory.ATHLETES, 1600, "🇺🇸", "Fast breaks and crushing counterattacks.", BotAvatarStyle(skinColor = 0xFF795548, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFD7CCC8, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_giannis", "Giannis", "Freak", BotCategory.ATHLETES, 1600, "🇬🇷", "Defending the paint and protecting the king.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF1B5E20, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_luka", "Luka", "Point Guard", BotCategory.ATHLETES, 1650, "🇸🇮", "Step-back queen maneuvers.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFFFFA000, shirtColor = 0xFF0288D1, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_lebron", "LeBron", "Hoops Legend", BotCategory.ATHLETES, 1700, "🇺🇸", "Vision across the whole court and whole board.", BotAvatarStyle(skinColor = 0xFF5D4037, hairStyle = HairType.SHORT, hairColor = 0xFF212121, facialHair = FacialHairType.FULL_BEARD, shirtColor = 0xFF0D47A1, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_kylian", "Kylian", "Speedster", BotCategory.ATHLETES, 1750, "🇫🇷", "Speed in pawn storms.", BotAvatarStyle(skinColor = 0xFF8D5524, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFFECEFF1, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_shohei", "Shohei", "Dual Threat", BotCategory.ATHLETES, 1800, "🇯🇵", "Pitching solid defense and batting home attacks.", BotAvatarStyle(skinColor = 0xFFFFE0BD, hairStyle = HairType.SHORT, hairColor = 0xFF212121, shirtColor = 0xFF0D47A1, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false),
        BotProfile("ath_novak", "Novak", "Tennis Champion", BotCategory.ATHLETES, 1850, "🇷🇸", "Return every shot, outlast every opponent.", BotAvatarStyle(skinColor = 0xFFFFDBAC, hairStyle = HairType.SHORT, hairColor = 0xFF37474F, shirtColor = 0xFF2E7D32, accessory = AccessoryType.JERSEY), isUnlockedByDefault = false)
    )

    fun getBotById(id: String): BotProfile {
        return allBots.find { it.id == id } ?: bandBots.first()
    }

    fun getBotsByCategory(category: BotCategory): List<BotProfile> {
        return when (category) {
            BotCategory.BAND_CLASS -> bandBots
            BotCategory.BEGINNER -> beginnerBots
            BotCategory.INTERMEDIATE -> intermediateBots
            BotCategory.PRO -> proBots
            BotCategory.MASTER -> masterBots
            BotCategory.GRANDMASTER -> grandmasterBots
            BotCategory.ADAPTIVE -> adaptiveBots
            BotCategory.ATHLETES -> athleteBots
        }
    }
}
