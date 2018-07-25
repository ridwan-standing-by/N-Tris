package com.ridwanstandingby.ntris.polyomino.blueprint

import com.ridwanstandingby.ntris.polyomino.geometry.Array2D

data class PolyominoBlueprint(
        val rank: Int,
        val index: Int,
        val blockMatrix: Array2D<Boolean?>
)
