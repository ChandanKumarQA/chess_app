import re

files = [
    "KingVsKingDatabase.kt", "KingAndPawnDatabase.kt", "RookEndgameDatabase.kt",
    "QueenEndgameDatabase.kt", "BishopEndgameDatabase.kt", "KnightEndgameDatabase.kt",
    "PassedPawnsDatabase.kt", "EndgameStrategyDatabase.kt", "LucenaDatabase.kt", "PhilidorDatabase.kt"
]

data_dir = "app/src/main/java/com/chessmaster/play/data"

for f in files:
    content = open(f"{data_dir}/{f}").read()
    moves = re.findall(r"listOf\((.*?)\)", content)
    moves_clean = [m.replace("\"", "").replace(" ", "") for m in moves if m]
    unique_moves = len(set(moves_clean))
    print(f"{f}: {len(moves_clean)} total, {unique_moves} unique move sequences")
