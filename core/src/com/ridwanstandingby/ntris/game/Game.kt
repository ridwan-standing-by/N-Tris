package com.ridwanstandingby.ntris.game

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.events.Events
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer
import com.ridwanstandingby.ntris.polyomino.*
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader

class Game {

    private val clock = Clock()
    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(clock, eventHandler)
    private val polyominoBlueprintHolder = PolyominoBlueprintLoader().load()
    private val polyominoSpawner = PolyominoSpawner(polyominoBlueprintHolder)
    private val legalMoveHelper = LegalMoveHelper()
    private val completeLineChecker = CompleteLineChecker()
    private val pulser = TimedDebouncer(clock, GameRules.PULSE_TIME) { eventHandler.queue(Events.Pulse()) }
            .also { it.nowPressed = true }

    private var paused = false
    private var wasLastMoveDownSuccessful: Boolean = true
    private var wasLastSpawnPieceSuccessful: Boolean = true

    val score = Score(0, 0)

    val backgroundBlockMap = BlockMap()

    var currentPiece = polyominoSpawner.generatePolyomino(score).apply { setToPlaySpawnPosition() }
    var nextPiece = polyominoSpawner.generatePolyomino(score)
    var reservePiece = polyominoSpawner.generatePolyomino(score)

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
            completeLineChecker.checkLinesAndIncreaseScoreIfNecessary(backgroundBlockMap, score)
            currentPiece = nextPiece
            nextPiece = polyominoSpawner.generatePolyomino(score)
            wasLastMoveDownSuccessful = true
        }
    }

    private fun gameOver() {
        paused = true
        println("GAME OVER!")
    }
}
