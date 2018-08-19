package com.ridwanstandingby.ntris.data

import android.content.Context
import com.ridwanstandingby.ntris.game.Score

class AndroidDataManager(context: Context) : DataManager {

    private val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)

    override var highScore: Score
        get() = Score(
                prefs.getInt(HIGH_SCORE_POINTS_KEY, 0),
                prefs.getInt(HIGH_SCORE_LINES_KEY, 0))
        set(value) {
            prefs.edit()
                    .putInt(HIGH_SCORE_POINTS_KEY, value.points)
                    .putInt(HIGH_SCORE_LINES_KEY, value.lines)
                    .apply()
        }

    companion object {
        private const val PREFS_FILE_NAME = "NTRIS_PREFS"

        private const val HIGH_SCORE_POINTS_KEY = "high_score_points"
        private const val HIGH_SCORE_LINES_KEY = "high_score_lines"
    }
}
