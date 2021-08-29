package com.ridwanstandingby.ntris.data

import com.ridwanstandingby.ntris.game.SavedGame
import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader
import com.ridwanstandingby.ntris.render.views.LayoutArrangement

abstract class GameDataManager {

    abstract var savedGame: SavedGame?

    abstract var highScore: Score
    abstract var layoutArrangement: LayoutArrangement

    abstract val polyominoFileStrings: HashMap<String, String>?

    var polyominoBlueprintHolder: PolyominoBlueprintHolder? = null

    abstract fun registerScore(score: Score)

    fun loadPolyominoBlueprintsIfNecessary() {
        if (polyominoBlueprintHolder == null) {
            polyominoBlueprintHolder = PolyominoBlueprintLoader(polyominoFileStrings).load()
        }
    }
}
