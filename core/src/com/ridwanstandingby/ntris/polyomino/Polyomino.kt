package com.ridwanstandingby.ntris.polyomino

import com.badlogic.gdx.graphics.Color
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprint
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

class Polyomino(private val polyominoBlueprint: PolyominoBlueprint,
                var position: IntVector2,
                private val colour: Color) {
    val size = IntVector2(polyominoBlueprint.blockMatrix.xSize, polyominoBlueprint.blockMatrix.ySize)
    val positionalCentre = size / 2
    val rotationalCentre = size / 2f // TODO can be a block or a grid corner
    val relativeCoordinates: Set<IntVector2> = polyominoBlueprint.blockMatrix.toCoordinateSet { it != null && it }

    constructor(polyomino: Polyomino): this(polyomino.polyominoBlueprint, IntVector2(polyomino.position), polyomino.colour)

    fun generateBlocks(): List<Block> = this.relativeCoordinates.map {
        Block(this.position - this.positionalCentre + it, this.colour)
    }

    fun moveDown() {
        position += IntVector2(0, -1)
    }

    fun moveLeft() {
        position += IntVector2(-1, 0)
    }

    fun moveRight() {
        position += IntVector2(1, 0)
    }
}
