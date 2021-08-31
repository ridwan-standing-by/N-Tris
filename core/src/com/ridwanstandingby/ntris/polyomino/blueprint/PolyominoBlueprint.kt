package com.ridwanstandingby.ntris.polyomino.blueprint

import com.ridwanstandingby.ntris.polyomino.geometry.Array2D
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

data class PolyominoBlueprint(
    val rank: Int,
    val index: Int,
    val blockMatrix: Array2D<Boolean?>
) {

    fun generateAbsoluteCoordinates() = mutableSetOf<IntVector2>().also { list ->
        blockMatrix.forEachIndexed { x, y, b ->
            if (b != null && b) list.add(
                IntVector2(x, y)
            )
        }
    }.toSet()
}
