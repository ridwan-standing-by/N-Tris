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
    private val generousRotator = GenerousRotator { piece, moves -> tryPieceMoves(piece, moves) }
    private val completeLineChecker = CompleteLineChecker()
    private val pulser = TimedDebouncer(clock, GameRules.PULSE_TIME) { eventHandler.queue(Events.Pulse()) }
            .also { it.nowPressed = true }

    private var paused = false
    private var hasSwappedReserve = false
    private var hasRerolledNext = false

    val score = Score(0, 0)
    val highScore = Score(0, 0) // TODO

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
            tryPieceMoves(polyomino, listOf(move))

    private fun tryPieceMoves(polyomino: Polyomino, moves: Iterable<Polyomino.() -> Unit>): Boolean =
            legalMoveHelper.ifMoveSequenceIsLegalThenDoMoves(polyomino, backgroundBlockMap, moves)

    fun currentPieceMoveDown() = tryPieceMove(currentPiece) { moveDown() }

    fun currentPieceMoveLeft() = tryPieceMove(currentPiece) { moveLeft() }

    fun currentPieceMoveRight() = tryPieceMove(currentPiece) { moveRight() }

    fun currentPieceRotateLeft() = generousRotator.tryPieceRotateLeft(currentPiece)

    fun currentPieceRotateRight() = generousRotator.tryPieceRotateRight(currentPiece)

    fun togglePause() {
        paused = !paused
    }

    fun swapReserveAttempt() {
        if (!hasSwappedReserve) {
            currentPiece = reservePiece.also { reservePiece = currentPiece }
            reservePiece.resetPositionToOrigin()
            currentPiece.setToPlaySpawnPosition()
            hasSwappedReserve = true
        }
    }

    fun nextRerollAttempt() {
        if (!hasRerolledNext) {
            nextPiece = polyominoSpawner.generatePolyomino(score)
            hasRerolledNext = true
        }
    }

    fun pulse() {
        when {
            currentPieceMoveDown() -> {
            }
            tryCyclePiece() -> {
            }
            else -> gameOver()
        }
    }

    private fun tryCyclePiece(): Boolean {
        backgroundBlockMap.blocks.addAll(currentPiece.generateBlocks())
        val wasLastSpawnPieceSuccessful = tryPieceMove(nextPiece) { setToPlaySpawnPosition() }
        return if (wasLastSpawnPieceSuccessful) {
            doCyclePieceAndResolveLines()
            true
        } else
            false
    }

    private fun doCyclePieceAndResolveLines() {
        completeLineChecker.checkLinesAndIncreaseScoreIfNecessary(backgroundBlockMap, score)
        currentPiece = nextPiece
        nextPiece = polyominoSpawner.generatePolyomino(score)
        hasSwappedReserve = false
        hasRerolledNext = false
    }

    private fun gameOver() {
        paused = true
        println("GAME OVER!")
    }
}
