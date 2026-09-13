#!/usr/bin/env python3
import chess
import os

def fen_from_dicts(white_pieces, black_pieces, turn="w", castling="-", ep="-", halfmove=0, fullmove=1):
    board = [["." for _ in range(8)] for _ in range(8)]
    for sq, p in white_pieces.items():
        c = ord(sq[0]) - ord('a')
        r = 8 - int(sq[1])
        board[r][c] = p.upper()
    for sq, p in black_pieces.items():
        c = ord(sq[0]) - ord('a')
        r = 8 - int(sq[1])
        board[r][c] = p.lower()

    rows = []
    for r in range(8):
        empty = 0
        row_str = ""
        for c in range(8):
            cell = board[r][c]
            if cell == ".":
                empty += 1
            else:
                if empty > 0:
                    row_str += str(empty)
                    empty = 0
                row_str += cell
        if empty > 0:
            row_str += str(empty)
        rows.append(row_str)
    return f"{'/'.join(rows)} {turn} {castling} {ep} {halfmove} {fullmove}"

def verify_puzzle(fen, moves):
    try:
        b = chess.Board(fen)
        if b.status() & chess.STATUS_OPPOSITE_CHECK:
            return False, "Opponent King is already in check initially"
        for m_str in moves:
            m = chess.Move.from_uci(m_str)
            if m not in b.legal_moves:
                return False, f"Illegal move {m_str} in position {b.fen()}"
            b.push(m)
        return True, "OK"
    except Exception as e:
        return False, str(e)

def interleave_diverse(puzzles, target_count=100):
    # Group by first move
    by_first = {}
    for p in puzzles:
        fm = p[1][0]
        if fm not in by_first:
            by_first[fm] = []
        by_first[fm].append(p)
    
    result = []
    keys = sorted(by_first.keys(), key=lambda k: len(by_first[k]))
    idx = 0
    while len(result) < target_count and keys:
        k = keys[idx % len(keys)]
        if by_first[k]:
            result.append(by_first[k].pop(0))
            if not by_first[k]:
                keys.remove(k)
        idx += 1
    
    # If still needed
    if len(result) < target_count:
        for p in puzzles:
            if p not in result:
                result.append(p)
                if len(result) == target_count:
                    break
    return result[:target_count]

def format_database_kt(class_name, theme_name, puzzles):
    lines = [
        "package com.chessmaster.play.data",
        "",
        "import com.chessmaster.play.model.Puzzle",
        "",
        f"object {class_name} {{",
        "",
        "    private data class RawTacticalPuzzle(",
        "        val fen: String,",
        "        val solutionMoves: List<String>,",
        "        val motif: String",
        "    )",
        "",
        "    private val pool = listOf("
    ]
    for i, (fen, moves, motif) in enumerate(puzzles):
        comma = "," if i < len(puzzles) - 1 else ""
        escaped_motif = motif.replace('"', '\\"')
        moves_str = ", ".join(f'"{m}"' for m in moves)
        lines.append(f'        RawTacticalPuzzle("{fen}", listOf({moves_str}), "{escaped_motif}"){comma}')
    lines.extend([
        "    )",
        "",
        "    private var cachedPuzzles: List<Puzzle>? = null",
        "",
        "    fun getPuzzles(): List<Puzzle> {",
        "        cachedPuzzles?.let { return it }",
        "        val puzzles = (1..100).map { i ->",
        "            val base = pool[i - 1]",
        "            val tierRating = when {",
        "                i <= 35 -> 650 + (i * 10)",
        "                i <= 70 -> 1100 + ((i - 35) * 15)",
        "                else -> 1700 + ((i - 70) * 20)",
        "            }",
        f'            val cleanTheme = "{theme_name}"',
        '            val idPrefix = cleanTheme.lowercase().replace(" ", "_")',
        "            Puzzle(",
        '                id = "tac_${idPrefix}_$i",',
        "                fen = base.fen,",
        "                solutionMoves = base.solutionMoves,",
        "                rating = tierRating,",
        '                theme = "$cleanTheme - ${base.motif}",',
        "                xpReward = 15 + (i / 10),",
        "                coinsReward = 8 + (i / 20)",
        "            )",
        "        }",
        "        cachedPuzzles = puzzles",
        "        return puzzles",
        "    }",
        "}"
    ])
    return "\n".join(lines) + "\n"
