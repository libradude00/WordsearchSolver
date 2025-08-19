package com.example.wordsearchsolver

class WordsearchSolver(private val grid: Array<CharArray>) {

    private val directions = listOf(
        Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0),
        Pair(1, 1), Pair(1, -1), Pair(-1, 1), Pair(-1, -1)
    )

    fun findWord(word: String): List<Pair<Int, Int>>? {
        val rows = grid.size
        val cols = grid[0].size
        val target = word.uppercase()

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (grid[r][c].uppercaseChar() == target[0]) {
                    for ((dr, dc) in directions) {
                        var rr = r
                        var cc = c
                        var matched = true
                        val positions = mutableListOf<Pair<Int, Int>>()

                        for (i in target.indices) {
                            if (rr !in 0 until rows || cc !in 0 until cols || grid[rr][cc].uppercaseChar() != target[i]) {
                                matched = false
                                break
                            }
                            positions.add(Pair(rr, cc))
                            rr += dr
                            cc += dc
                        }

                        if (matched) return positions
                    }
                }
            }
        }
        return null
    }

    fun solve(words: List<String>): Map<String, List<Pair<Int, Int>>?> {
        return words.associateWith { findWord(it) }
    }
}
