package com.ridwanstandingby.ntris.data

import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader
import com.ridwanstandingby.ntris.render.views.LayoutArrangement

abstract class DataManager {

    abstract var highScore: Score
    abstract var layoutArrangement: LayoutArrangement

    abstract val polyominoFileStrings: HashMap<String, String>?

    var polyominoBlueprintHolder: PolyominoBlueprintHolder? = null

    fun loadPolyominoBlueprints() {
        polyominoBlueprintHolder = PolyominoBlueprintLoader(polyominoFileStrings).load()
    }
}