package com.ridwanstandingby.ntris.game

import com.badlogic.gdx.Gdx
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.Event
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer
import com.ridwanstandingby.ntris.polyomino.*
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader

class Game(private val gameDataManager: GameDataManager) {

    private val clock = Clock()
    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(clock, eventHandler)
    private val polyominoBlueprintHolder = gameDataManager.polyominoBlueprintHolder?: PolyominoBlueprintLoader().load()
    private val polyominoSpawner = PolyominoSpawner(polyominoBlueprintHolder)
    private val legalMoveHelper = LegalMoveHelper()
    private val generousPieceManipulator = GenerousPieceManipulator { piece, moves -> tryPieceMoves(piece, moves) }
    private val completeLineChecker = CompleteLineChecker()
    private val pulser = TimedDebouncer(clock, GameRules.PULSE_TIME,
            { _ -> true },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.Pulse()) })

    var score = Score(0, 0)
    val highScore = gameDataManager.highScore.copy()

    var isGameOver = false
    var doRestart = false
    var isPaused = false
    fun isInPlay() = (isGameOver or isPaused).not()

    val backgroundBlockMap = BlockMap()
    var currentPiece = polyominoSpawner.generatePolyomino(score).apply { setToPlaySpawnPosition() }
    var nextPiece = polyominoSpawner.generatePolyomino(score)
    var reservePiece = polyominoSpawner.generatePolyomino(score)
    private var hasSwappedReserve = false

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(this, rawPlayInput)
        pulser.update(RawPlayInput())
        pulser.invokeDebounced(RawPlayInput(), this)
        pulser.cycle()
    }

    fun update(dt: Float) {
        eventHandler.handleEvents(this)
        clock.tick(dt)
    }

    private fun tryPieceMove(polyomino: Polyomino, move: Polyomino.() -> Unit): Boolean =
            tryPieceMoves(polyomino, listOf(move))

    private fun tryPieceMoves(polyomino: Polyomino, moves: Iterable<Polyomino.() -> Unit>): Boolean =
            legalMoveHelper.ifMoveSequenceIsLegalThenDoMoves(polyomino, backgroundBlockMap, moves)

    fun currentPieceMoveDown() = tryPieceMove(currentPiece) { moveDown() }

    fun currentPieceMoveLeft() = tryPieceMove(currentPiece) { moveLeft() }

    fun currentPieceMoveRight() = tryPieceMove(currentPiece) { moveRight() }

    fun currentPieceRotateLeft() = generousPieceManipulator.tryPieceRotateLeft(currentPiece)

    fun currentPieceRotateRight() = generousPieceManipulator.tryPieceRotateRight(currentPiece)

    fun currentPieceReflect() = generousPieceManipulator.tryPieceReflect(currentPiece)

    fun swapReserveAttempt() {
        if (!hasSwappedReserve) {
            currentPiece = reservePiece.also { reservePiece = currentPiece }
            reservePiece.resetPositionToOrigin()
            currentPiece.setToPlaySpawnPosition()
            hasSwappedReserve = true
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
        score += completeLineChecker.checkAndDestroyLinesAndCalculateScoreIncrease(backgroundBlockMap)
        updateHighScoreIfNecessary()
        currentPiece = nextPiece
        nextPiece = polyominoSpawner.generatePolyomino(score)
        hasSwappedReserve = false
    }

    private fun updateHighScoreIfNecessary() {
        if (score > highScore)
            gameDataManager.highScore = score
    }

    private fun gameOver() {
        isGameOver = true
        gameDataManager.registerScore(score)
    }

    fun pause() {
        isPaused = true
    }

    fun resume() {
        isPaused = false
    }

    fun back() {
        if (isInPlay()) {
            isPaused = true
        } else {
            exit()
        }
    }

    fun restartGame() {
        doRestart = true
    }

    private fun exit() {
        Gdx.app.exit()
    }
}
