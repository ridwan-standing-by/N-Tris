package com.ridwanstandingby.ntris.data

import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader

abstract class DataManager {

    abstract var highScore: Score
    abstract val polyominoFileStrings: HashMap<String, String>?

    var polyominoBlueprintHolder: PolyominoBlueprintHolder? = null

    fun loadPolyominoBlueprints() {
        polyominoBlueprintHolder = PolyominoBlueprintLoader(polyominoFileStrings).load()
    }
}