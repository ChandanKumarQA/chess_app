#!/usr/bin/env python3
import os
import sys
sys.path.insert(0, 'scratch')
import generate_perfect_13 as g
from tactical_master_builder import verify_puzzle, format_database_kt

def run_build():
    databases = [
        ("MateIn1Database", "Mate in 1", g.gen_mate_in_1),
        ("MateIn2Database", "Mate in 2", g.gen_mate_in_2),
        ("ForkDatabase", "Fork", g.gen_fork),
        ("PinDatabase", "Pin", g.gen_pin),
        ("SkewerDatabase", "Skewer", g.gen_skewer),
        ("DoubleAttackDatabase", "Double Attack", g.gen_double_attack),
        ("DiscoveredAttackDatabase", "Discovered Attack", g.gen_discovered_attack),
        ("SmotheredMateDatabase", "Smothered Mate", g.gen_smothered_mate),
        ("SacrificeDatabase", "Sacrifice", g.gen_sacrifice),
        ("AttractionDatabase", "Attraction", g.gen_attraction),
        ("DeflectionDatabase", "Deflection", g.gen_deflection),
        ("ClearanceDatabase", "Clearance", g.gen_clearance),
        ("WinningMaterialDatabase", "Winning Material", g.gen_winning_material)
    ]

    out_dir = "app/src/main/java/com/chessmaster/play/data"

    print("=== BUILDING AND WRITING ALL 13 TACTICAL DATABASES ===")
    for cls_name, theme_name, gen_func in databases:
        puzzles = gen_func()
        assert len(puzzles) == 100, f"Error: {cls_name} generated {len(puzzles)} instead of 100!"

        # Verify all 100
        for i, (fen, moves, motif) in enumerate(puzzles):
            ok, err = verify_puzzle(fen, moves)
            assert ok, f"Error verifying {cls_name} Level {i+1}: {err}"

        # Verify uniqueness
        fens = [p[0] for p in puzzles]
        assert len(set(fens)) == 100, f"Error: {cls_name} has {len(fens) - len(set(fens))} duplicate FENs!"

        # Count consecutive identical first moves
        consec_same = sum(1 for i in range(1, 100) if puzzles[i][1][0] == puzzles[i-1][1][0])
        unique_fms = len(set(p[1][0] for p in puzzles))
        print(f"[{cls_name}] 100 legal puzzles, 100 unique FENs, {unique_fms} unique first moves, consecutive same: {consec_same}")

        # Write Kotlin file
        kt_code = format_database_kt(cls_name, theme_name, puzzles)
        file_path = os.path.join(out_dir, f"{cls_name}.kt")
        with open(file_path, "w") as f:
            f.write(kt_code)
        print(f"  -> Successfully wrote {file_path}")

    print("\nALL 13 TACTICAL DATABASES WRITTEN WITH 100% UNIQUE POSITIONS AND VERIFIED LEGAL MOVES!")

if __name__ == "__main__":
    run_build()
