#!/usr/bin/env python3
import chess
import random
import os
from tactical_builder_core import fen_from_dicts, verify_moves, format_kt_file

def build_all():
    print("Starting generation of 100% unique tactical databases...")
    
    # We will generate and write each of the 13 tactical databases
    # Let us implement modular generators for all 13 themes
    import generate_all_tactical_modules as g
    
    databases = [
        ("SkewerDatabase", "Skewer", g.get_skewer_100),
        ("ForkDatabase", "Fork", g.get_fork_100),
        ("PinDatabase", "Pin", g.get_pin_100),
        ("DoubleAttackDatabase", "Double Attack", g.get_double_attack_100),
        ("DiscoveredAttackDatabase", "Discovered Attack", g.get_discovered_attack_100),
        ("SmotheredMateDatabase", "Smothered Mate", g.get_smothered_mate_100),
        ("MateIn1Database", "Mate in 1", g.get_mate_in_1_100),
        ("MateIn2Database", "Mate in 2", g.get_mate_in_2_100),
        ("SacrificeDatabase", "Sacrifice", g.get_sacrifice_100),
        ("AttractionDatabase", "Attraction", g.get_attraction_100),
        ("DeflectionDatabase", "Deflection", g.get_deflection_100),
        ("ClearanceDatabase", "Clearance", g.get_clearance_100),
        ("WinningMaterialDatabase", "Winning Material", g.get_winning_material_100)
    ]
    
    base_dir = "app/src/main/java/com/chessmaster/play/data"
    
    for cls_name, theme_name, gen_func in databases:
        print(f"Generating {cls_name} ({theme_name})...")
        puzzles = gen_func()
        assert len(puzzles) == 100, f"{cls_name} returned {len(puzzles)} puzzles instead of 100"
        
        # Verify all 100
        for i, (fen, moves, motif) in enumerate(puzzles):
            ok, err = verify_moves(fen, moves)
            assert ok, f"Error in {cls_name} level {i+1}: {err}"
            
        # Check uniqueness of FENs
        fens = [p[0] for p in puzzles]
        assert len(set(fens)) == 100, f"{cls_name} has duplicate FENs: {len(fens)} vs {len(set(fens))} unique"
        
        # Check that consecutive first moves are not all identical
        consecutive_same = 0
        for i in range(1, 100):
            if puzzles[i][1][0] == puzzles[i-1][1][0]:
                consecutive_same += 1
        print(f"  -> {cls_name}: 100 legal, 100 unique FENs, consecutive same first move count = {consecutive_same}")
        
        content = format_kt_file(cls_name, theme_name, puzzles)
        file_path = os.path.join(base_dir, f"{cls_name}.kt")
        with open(file_path, "w") as f:
            f.write(content)
        print(f"  -> Wrote {file_path}")

    print("\nALL 13 TACTICAL DATABASES SUCCESSFULLY GENERATED AND WRITTEN!")

if __name__ == "__main__":
    build_all()
