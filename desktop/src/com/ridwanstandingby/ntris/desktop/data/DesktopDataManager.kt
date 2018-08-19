package com.ridwanstandingby.ntris.desktop.data

import com.ridwanstandingby.ntris.data.DataManager
import com.ridwanstandingby.ntris.game.Score
import java.io.File

class DesktopDataManager : DataManager {

    init {
        File(PREFS_FILE_NAME).createNewFile()
    }

    override var highScore: Score
        get() = Score(
                getValue(HIGH_SCORE_POINTS_KEY, 0),
                getValue(HIGH_SCORE_LINES_KEY, 0)
        )
        set(value) {
            setValue(HIGH_SCORE_POINTS_KEY, value.points)
            setValue(HIGH_SCORE_LINES_KEY, value.lines)
        }

    private inline fun <reified T> getValue(key: String, default: T): T =
            try {
                File(PREFS_FILE_NAME)
                        .useLines { it.toList() }
                        .firstOrNull { it.contains(key) && it.contains(PREFS_DELIMITER) }
                        ?.split(PREFS_DELIMITER)
                        ?.let { it[1].toPrimitive<T>() } ?: default
            } catch (e: Exception) {
                default
            }

    private inline fun <reified T> setValue(key: String, value: T) {
        val oldLines = File(PREFS_FILE_NAME)
                .useLines { it.toList() }

        var lineFound = false
        val newLines = mutableListOf<String>()
        val lineToBeAdded = key + PREFS_DELIMITER + value.toString()
        oldLines.forEach {
            if (it.contains(key) && it.contains(PREFS_DELIMITER)) {
                lineFound = true
                newLines.add(lineToBeAdded)
            } else {
                newLines.add(it)
            }
        }

        if (!lineFound) newLines.add(lineToBeAdded)

        val newFileString = StringBuilder().apply { newLines.forEach { appendln(it) } }.toString()

        File(PREFS_FILE_NAME).writeText(newFileString)
    }

    private inline fun <reified T> String.toPrimitive(): T? =
            when (T::class) {
                Int::class -> this.toInt() as T
                Short::class -> this.toShort() as T
                Long::class -> this.toLong() as T
                Float::class -> this.toFloat() as T
                Double::class -> this.toFloat() as T
                Boolean::class -> this.toBoolean() as T
                Byte::class -> this.toByte() as T
                String::class -> this as T
                else -> null
            }

    companion object {
        private const val PREFS_FILE_NAME = "NTrisSettings.txt"
        private const val PREFS_DELIMITER = "="

        private const val HIGH_SCORE_POINTS_KEY = "high_score_points"
        private const val HIGH_SCORE_LINES_KEY = "high_score_lines"
    }
}
