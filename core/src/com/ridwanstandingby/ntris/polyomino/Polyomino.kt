package com.ridwanstandingby.ntris.polyomino

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprint
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import ktx.math.plus

data class Polyomino(private val polyominoBlueprint: PolyominoBlueprint,
                     private val colour: Color,
                     private var position: IntVector2) {
    private val size = IntVector2(polyominoBlueprint.blockMatrix.xSize, polyominoBlueprint.blockMatrix.ySize)
    private val positionalCentre = size / 2
    private val rotationalCentre = Vector2(size.x / 2f, size.y / 2f)
    private var relativeCoordinates: Set<IntVector2> = polyominoBlueprint.generateCoordinates()

    fun copy(): Polyomino = Polyomino(polyominoBlueprint, colour, position).also {
        it.relativeCoordinates = this.relativeCoordinates
    }

    fun generateBlocks(): List<Block> = relativeCoordinates.map { coordinate ->
        Block(position - positionalCentre + coordinate, colour)
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

    fun rotateLeft() {
        rotate(rotateLeftRule)
    }

    fun rotateRight() {
        rotate(rotateRightRule)
    }

    private fun rotate(rotateRule: (Vector2) -> Vector2) {
        val oldRotationalCoordinates = relativeCoordinates.map { it - rotationalCentre }
        val newRotationalCoordinates = oldRotationalCoordinates.map { rotateRule(it) }
        relativeCoordinates = newRotationalCoordinates.map { IntVector2(it + rotationalCentre) }.toSet()
    }

    companion object {
        private val rotateLeftRule = { v: Vector2 -> Vector2(-v.y, v.x) }
        private val rotateRightRule = { v: Vector2 -> Vector2(v.y, -v.x) }
    }
}
