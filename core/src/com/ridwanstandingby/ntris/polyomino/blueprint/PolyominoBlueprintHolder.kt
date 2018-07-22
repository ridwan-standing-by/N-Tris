package com.ridwanstandingby.ntris.polyomino.blueprint

import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MIN_RANK

class PolyominoBlueprintHolder {

    val polyominoBlueprints = mutableListOf(mutableListOf<PolyominoBlueprint>())

    companion object {
        fun rankToIndex(rank: Int) = rank - MIN_RANK
    }
}
