package com.ridwanstandingby.ntris.polyomino

import com.badlogic.gdx.graphics.Color
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprint
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

class Polyomino(polyominoBlueprint: PolyominoBlueprint,
                var position: IntVector2,
                val colour: Color) {

    val size = IntVector2(polyominoBlueprint.blockMatrix.xSize, polyominoBlueprint.blockMatrix.ySize)
    val positionalCentre = size/2
    val rotationalCentre = size/2f
    val blocks: Set<IntVector2> = polyominoBlueprint.blockMatrix.toCoordinateSet {it != null && it}
}
