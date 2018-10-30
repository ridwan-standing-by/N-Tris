package com.ridwanstandingby.ntris.data

import android.content.Context
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import com.ridwanstandingby.ntris.domain.ScoreEntry
import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MAX_RANK
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MIN_RANK
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import java.util.*

class AndroidGameDataManager(private val context: Context,
                             private val sharedPreferencesManager: SharedPreferencesManager,
                             private val remoteDataManager: RemoteDataManager) : GameDataManager() {

    override var highScore: Score
        get() = sharedPreferencesManager.highScore
        set(value) {
            sharedPreferencesManager.highScore = value
        }

    override var layoutArrangement: LayoutArrangement
        get() = sharedPreferencesManager.layoutArrangement
        set(value) {
            sharedPreferencesManager.layoutArrangement = value
        }

    override val polyominoFileStrings = hashMapOf<String, String>().also { hashMap ->
        (MIN_RANK..MAX_RANK).forEach { rank ->
            val fileName = PolyominoBlueprintLoader.BLUEPRINT_FILE_NAME_TEMPLATE.format(rank)
            val fileText = context.assets.open("polyominos/$fileName.txt").bufferedReader().use { it.readText() }
            hashMap[fileName] = fileText
        }
    }

    override fun registerScore(score: Score) {
        val scoreEntry = ScoreEntry(
                time = Date(),
                name = sharedPreferencesManager.nickname,
                score = score.points.toLong(),
                lines = score.lines.toLong())
        remoteDataManager.uploadScoreEntry(scoreEntry, onError = { it.printStackTrace() })
    }
}
