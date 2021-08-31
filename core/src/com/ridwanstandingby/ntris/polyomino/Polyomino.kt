package com.ridwanstandingby.ntris.polyomino

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.GameRules
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprint
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import ktx.math.plus

class Polyomino(
    val polyominoBlueprint: PolyominoBlueprint,
    val colour: Color,
    var position: Vector2 = Vector2(0f, 0f),
    var relativeCoordinates: Set<Vector2> = setOf()
) {

    init {
        if (relativeCoordinates.isEmpty()) {
            relativeCoordinates = calculateRelativeCoordinates()
        }
    }

    private fun calculateRelativeCoordinates(): Set<Vector2> {
        val absoluteCoordinates = polyominoBlueprint.generateAbsoluteCoordinates()
        val absoluteCentre =
            getCentre(absoluteCoordinates.map { Vector2(it.x.toFloat(), it.y.toFloat()) }.toSet())
        return absoluteCoordinates.map {
            Vector2(
                it.x.toFloat() - absoluteCentre.x,
                it.y.toFloat() - absoluteCentre.y
            )
        }.toSet()
    }

    fun resetPositionToOrigin() {
        position = Vector2(0f, 0f)
    }

    fun setToPlaySpawnPosition() {
        position = Vector2(
            (GameRules.PLAY_BLOCK_SIZE.x / 2).toFloat(),
            GameRules.PLAY_BLOCK_SIZE.y - 1f + getCentre(relativeCoordinates).y
        )
    }

    fun copy(): Polyomino = Polyomino(polyominoBlueprint, colour, position).also {
        it.relativeCoordinates = this.relativeCoordinates
    }

    fun generateBlocks(): List<Block> = relativeCoordinates.map { coordinate ->
        Block(IntVector2(position + coordinate), colour)
    }

    fun moveDown() {
        position += Vector2(0f, -1f)
    }

    fun moveLeft() {
        position += Vector2(-1f, 0f)
    }

    fun moveRight() {
        position += Vector2(1f, 0f)
    }

    fun rotateLeft() {
        manipulate(rotateLeftRule)
    }

    fun rotateRight() {
        manipulate(rotateRightRule)
    }

    fun reflect() {
        manipulate(reflectRule)
    }

    private fun manipulate(manipulateRule: (Vector2) -> Vector2) {
        relativeCoordinates = relativeCoordinates.map { manipulateRule(it) }.toSet()
    }

    companion object {

        private fun getSize(coordinates: Set<Vector2>) = Vector2(
            (coordinates.maxByOrNull { it.x }?.x ?: 0f)
                    - (coordinates.minByOrNull { it.x }?.x ?: 0f),
            (coordinates.maxByOrNull { it.y }?.y ?: 0f)
                    - (coordinates.minByOrNull { it.y }?.y ?: 0f)
        )

        private fun getCentre(coordinates: Set<Vector2>) =
            getSize(coordinates).let { Vector2(it.x / 2f, it.y / 2f) }

        private val rotateLeftRule = { v: Vector2 -> Vector2(-v.y, v.x) }
        private val rotateRightRule = { v: Vector2 -> Vector2(v.y, -v.x) }
        private val reflectRule = { v: Vector2 -> Vector2(-v.x, v.y) }
    }
}
