package com.ridwanstandingby.ntris.data

import android.content.Context
import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MAX_RANK
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MIN_RANK
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader
import com.ridwanstandingby.ntris.render.views.LayoutArrangement

class AndroidDataManager(private val context: Context) : DataManager() {

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

    override var layoutArrangement: LayoutArrangement
        get() = LayoutArrangement.fromCode(
                prefs.getString(LAYOUT_ARRANGEMENT_CODE_KEY,
                LayoutArrangement.DEFAULT_LAYOUT_ARRANGEMENT.code)?:
                LayoutArrangement.DEFAULT_LAYOUT_ARRANGEMENT.code)
        set(value) {
            prefs.edit()
                    .putString(LAYOUT_ARRANGEMENT_CODE_KEY, value.code)
                    .apply()
        }

    override val polyominoFileStrings = hashMapOf<String, String>().also { hashMap ->
        (MIN_RANK..MAX_RANK).forEach { rank ->
            val fileName = PolyominoBlueprintLoader.BLUEPRINT_FILE_NAME_TEMPLATE.format(rank)
            val fileText = context.assets.open("polyominos/$fileName.txt").bufferedReader().use { it.readText() }
            hashMap[fileName] = fileText
        }
    }


    companion object {
        private const val PREFS_FILE_NAME = "NTRIS_PREFS"

        private const val HIGH_SCORE_POINTS_KEY = "high_score_points"
        private const val HIGH_SCORE_LINES_KEY = "high_score_lines"

        private const val LAYOUT_ARRANGEMENT_CODE_KEY = "layout_arrangement_code"
    }
}
