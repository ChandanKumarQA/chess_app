#!/usr/bin/env python3
import chess
import random
import os

def fen_from_dicts(w_dict, b_dict, turn=chess.WHITE):
    board = chess.Board(None)
    for sq, p in w_dict.items():
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.upper()))
    for sq, p in b_dict.items():
        board.set_piece_at(chess.parse_square(sq), chess.Piece.from_symbol(p.lower()))
    board.turn = turn
    return board.fen()

def verify_moves(fen, moves):
    try:
        b = chess.Board(fen)
        for m in moves:
            mv = chess.Move.from_uci(m)
            if mv not in b.legal_moves:
                return False, f"Illegal move {m}"
            b.push(mv)
        return True, "OK"
    except Exception as e:
        return False, str(e)

def format_kt_file(class_name, theme_name, puzzles):
    assert len(puzzles) == 100, f"Expected 100 puzzles for {class_name}, got {len(puzzles)}"
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
        moves_str = ", ".join(f'"{m}"' for m in moves)
        comma = "," if i < 99 else ""
        escaped_motif = motif.replace('"', '\\"')
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
        "            val cleanTheme = \"" + theme_name + "\"",
        "            val idPrefix = cleanTheme.lowercase().replace(\" \", \"_\")",
        "            Puzzle(",
        "                id = \"tac_${idPrefix}_$i\",",
        "                fen = base.fen,",
        "                solutionMoves = base.solutionMoves,",
        "                rating = tierRating,",
        "                theme = \"$cleanTheme - ${base.motif}\",",
        "                xpReward = 15 + (i / 10),",
        "                coinsReward = 8 + (i / 20)",
        "            )",
        "        }",
        "        cachedPuzzles = puzzles",
        "        return puzzles",
        "    }",
        "}",
        ""
    ])
    return "\n".join(lines)

print("Base helper defined")
