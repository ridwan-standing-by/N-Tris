package com.ridwanstandingby.ntris.data.local

import android.content.Context
import com.ridwanstandingby.ntris.data.adapters.toJson
import com.ridwanstandingby.ntris.data.adapters.toSavedGame
import com.ridwanstandingby.ntris.game.SavedGame
import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import org.json.JSONException

class SharedPreferencesManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)

    var nickname: String
        get() = prefs.getString(NICKNAME_KEY, "") ?: ""
        set(value) {
            prefs.edit().putString(NICKNAME_KEY, value).apply()
        }

    var highScore: Score
        get() = Score(
            prefs.getInt(HIGH_SCORE_POINTS_KEY, 0),
            prefs.getInt(HIGH_SCORE_LINES_KEY, 0)
        )
        set(value) {
            prefs.edit()
                .putInt(HIGH_SCORE_POINTS_KEY, value.points)
                .putInt(HIGH_SCORE_LINES_KEY, value.lines)
                .apply()
        }

    var layoutArrangement: LayoutArrangement
        get() = LayoutArrangement.fromCode(
            prefs.getString(
                LAYOUT_ARRANGEMENT_CODE_KEY,
                LayoutArrangement.DEFAULT.code
            )
                ?: LayoutArrangement.DEFAULT.code
        )
        set(value) {
            prefs.edit()
                .putString(LAYOUT_ARRANGEMENT_CODE_KEY, value.code)
                .apply()
        }

    var savedGame: SavedGame?
        get() = try {
            prefs.getString(SAVED_GAME_KEY, null)?.toJson()?.toSavedGame()
        } catch (e: JSONException) {
            null
        }
        set(value) {
            try {
                prefs.edit()
                    .putString(SAVED_GAME_KEY, value?.toJson().toString())
                    .apply()
            } catch (e: JSONException) {

            }
        }

    companion object {
        private const val PREFS_FILE_NAME = "NTRIS_PREFS"

        private const val NICKNAME_KEY = "nickname"

        private const val HIGH_SCORE_POINTS_KEY = "high_score_points"
        private const val HIGH_SCORE_LINES_KEY = "high_score_lines"

        private const val LAYOUT_ARRANGEMENT_CODE_KEY = "layout_arrangement_code"

        private const val SAVED_GAME_KEY = "saved_game"
    }
}
