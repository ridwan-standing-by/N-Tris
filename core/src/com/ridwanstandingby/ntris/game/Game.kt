package com.ridwanstandingby.ntris.game

import com.badlogic.gdx.graphics.Color
import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.events.Events
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer
import com.ridwanstandingby.ntris.polyomino.Block
import com.ridwanstandingby.ntris.polyomino.BlockMap
import com.ridwanstandingby.ntris.polyomino.LegalMoveHelper
import com.ridwanstandingby.ntris.polyomino.Polyomino
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder.Companion.rankToIndex
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

class Game {

    private val clock = Clock()
    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(clock, eventHandler)
    private val polyominoBlueprintHolder = PolyominoBlueprintLoader().load()
    private val legalMoveHelper = LegalMoveHelper()
    private val pulser = TimedDebouncer(clock, GameRules.PULSE_TIME) { eventHandler.queue(Events.Pulse()) }
            .also { it.nowPressed = true }

    private var paused = false
    private var wasLastMoveDownSuccessful: Boolean = true
    private var wasLastSpawnPieceSuccessful: Boolean = true

    var currentPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(10)][40], Color.CYAN)
            .apply { setToPlaySpawnPosition() }
    var nextPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(8)][160], Color.CHARTREUSE)
    var reservePiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(7)][98], Color.FIREBRICK)
    var backgroundBlockMap = BlockMap().apply {
        blocks.add(Block(IntVector2(5, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(6, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 7), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 9), Color.GOLDENROD))
    }

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(rawPlayInput)
    }

    fun update(dt: Float) {
        eventHandler.handleEvents(this)
        pulser.invokeDebounced()
        if (!paused) clock.tick(dt)
    }

    private fun tryPieceMove(polyomino: Polyomino, move: Polyomino.() -> Unit): Boolean =
            legalMoveHelper.ifMoveIsLegalThenDoMove(polyomino, backgroundBlockMap) { move() }

    fun currentPieceMoveDown() = tryPieceMove(currentPiece) { moveDown() }

    fun currentPieceMoveLeft() = tryPieceMove(currentPiece) { moveLeft() }

    fun currentPieceMoveRight() = tryPieceMove(currentPiece) { moveRight() }

    fun currentPieceRotateLeft() = tryPieceMove(currentPiece) { rotateLeft() }

    fun currentPieceRotateRight() = tryPieceMove(currentPiece) { rotateRight() }

    fun togglePause() {
        paused = !paused
    }

    fun swapReserveAttempt() {
        currentPiece = reservePiece.also { reservePiece = currentPiece }
        reservePiece.resetPositionToOrigin()
        currentPiece.setToPlaySpawnPosition()
    }

    fun pulse() {
        when {
            wasLastMoveDownSuccessful -> currentPieceMoveDownFromPulse()
            currentPieceMoveDownFromPulse() -> {}
            wasLastSpawnPieceSuccessful -> spawnPiece()
            else -> gameOver()
        }
    }

    private fun currentPieceMoveDownFromPulse(): Boolean  =
            tryPieceMove(currentPiece) { moveDown() }.also { wasLastMoveDownSuccessful = it }

    private fun spawnPiece() {
        backgroundBlockMap.blocks.addAll(currentPiece.generateBlocks())
        wasLastSpawnPieceSuccessful = tryPieceMove(nextPiece) { setToPlaySpawnPosition() }
        if (wasLastSpawnPieceSuccessful) {
            currentPiece = nextPiece
            nextPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(6)][47], Color.OLIVE)
            wasLastMoveDownSuccessful = true
        }
    }

    private fun gameOver() {
        paused = true
        println("GAME OVER!")
    }
}
