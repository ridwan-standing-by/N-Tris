package com.ridwanstandingby.ntris.polyomino.blueprint

import com.badlogic.gdx.Gdx
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MAX_RANK
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MIN_RANK
import com.ridwanstandingby.ntris.polyomino.geometry.Array2D

class PolyominoBlueprintLoader(private val polyominoFileStrings: HashMap<String, String>? = null) {

    fun load(): PolyominoBlueprintHolder {
        val polyominoBlueprintHolder = PolyominoBlueprintHolder()
        (MIN_RANK..MAX_RANK).forEach { rank ->
            val lines = loadFileLines(rank)
            val polyominoBlueprintList = parseLinesAndAddPolyominosToList(lines, rank)
            polyominoBlueprintHolder.polyominoBlueprints.add(polyominoBlueprintList)
        }
        removeEmptyLists(polyominoBlueprintHolder)
        return polyominoBlueprintHolder
    }

    private fun parseLinesAndAddPolyominosToList(lines: Iterator<String>, rank: Int) =
        mutableListOf<PolyominoBlueprint>().also { list ->
            while (lines.hasNext()) {
                val line = lines.next()
                if (line[0] == INFO_LINE_BEGINNING_CHARACTER) {
                    list.add(parsePolyomino(line, lines, rank))
                }
            }
        }

    private fun loadFileLines(rank: Int): Iterator<String> =
        if (polyominoFileStrings != null) {
            (polyominoFileStrings[BLUEPRINT_FILE_NAME_TEMPLATE.format(rank)]
                ?.split(LINE_SEPARATOR) ?: listOf())
                .iterator()
        } else {
            Gdx.files.internal(BLUEPRINT_FILE_PATH_TEMPLATE.format(rank))
                .readString()
                .split(LINE_SEPARATOR)
                .iterator()
        }

    private fun parsePolyomino(
        line: String,
        lines: Iterator<String>,
        rank: Int
    ): PolyominoBlueprint {
        val index = line.substring(INDEX_POSITION).stripWhitespaceAndConvertToInt()
        val width = line.substring(WIDTH_POSITION).stripWhitespaceAndConvertToInt()
        val height = line.substring(HEIGHT_POSITION).stripWhitespaceAndConvertToInt()
        val matrix = parseBlockMatrix(width, height, lines)
        return PolyominoBlueprint(rank, index, matrix)
    }

    private fun String.stripWhitespaceAndConvertToInt() = replace(" ", "").toInt()

    private fun parseBlockMatrix(width: Int, height: Int, lines: Iterator<String>) =
        Array2D<Boolean>(width, height).also { matrix ->
            (0 until height).forEach { y ->
                val row = lines.next()
                row.forEachIndexed { x, c ->
                    matrix[x, y] = c == BLOCK_CHARACTER
                }
            }
        }

    private fun removeEmptyLists(polyominoBlueprintHolder: PolyominoBlueprintHolder) {
        // Creating a list of lists has the side effect of creating an empty one at the beginning
        polyominoBlueprintHolder.polyominoBlueprints.removeAt(0)
    }

    companion object {
        const val BLUEPRINT_FILE_NAME_TEMPLATE = "polyominos_%02d"
        const val BLUEPRINT_FILE_PATH_TEMPLATE = "polyominos/$BLUEPRINT_FILE_NAME_TEMPLATE.txt"
        private const val LINE_SEPARATOR = "\r\n"
        private const val INFO_LINE_BEGINNING_CHARACTER = '@'
        private const val BLOCK_CHARACTER = '#'
        private val INDEX_POSITION = 2..10
        private val WIDTH_POSITION = 11..13
        private val HEIGHT_POSITION = 14..16
    }
}
