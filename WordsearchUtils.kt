package com.example.wordsearchsolver

object WordsearchUtils {

    fun parseGridFromText(text: String): Array<CharArray> {
        val lines = text.split("\n".toRegex()).map { it.trim() }.filter { it.isNotEmpty() }
        val grid = mutableListOf<CharArray>()
        for (line in lines) {
            val row = line.split(" ", "").filter { it.isNotBlank() }.map { it[0].uppercaseChar() }
            grid.add(row.toCharArray())
        }
        return grid.toTypedArray()
    }

    fun formatSolution(solution: Map<String, List<Pair<Int, Int>>?>): String {
        val builder = StringBuilder()
        for ((word, coords) in solution) {
            builder.append(word.uppercase()).append(": ")
            if (coords != null) {
                builder.append(coords.joinToString(" -> ") { "(${it.first},${it.second})" })
            } else {
                builder.append("NOT FOUND")
            }
            builder.append("\n")
        }
        return builder.toString()
    }
}
