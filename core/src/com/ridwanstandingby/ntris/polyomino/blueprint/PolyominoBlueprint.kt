package com.ridwanstandingby.ntris.polyomino.blueprint

import com.ridwanstandingby.ntris.polyomino.Array2D

data class PolyominoBlueprint(
        val rank: Int,
        val index: Int,
        val width: Int,
        val height: Int,
        val blockMatrix: Array2D<Boolean?>
)
