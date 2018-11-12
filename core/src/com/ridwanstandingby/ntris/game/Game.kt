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

class Game(private val gameDataManager: GameDataManager,
           private val polyominoSpawner: PolyominoSpawner,
           private val clock: Clock = Clock(),
           var score: Score = Score(0, 0),
           var isGameOver: Boolean = false,
           var doRestart: Boolean = false,
           var isPaused: Boolean = false,
           private var hasSwappedReserve: Boolean = false,
           val backgroundBlockMap: BlockMap = BlockMap(),
           var currentPiece: Polyomino,
           var nextPiece: Polyomino,
           var reservePiece: Polyomino) {

    val highScore = gameDataManager.highScore.copy()
    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(clock, eventHandler)
    private val legalMoveHelper = LegalMoveHelper()
    private val generousPieceManipulator = GenerousPieceManipulator { piece, moves -> tryPieceMoves(piece, moves) }
    private val completeLineChecker = CompleteLineChecker()
    private val pulser = TimedDebouncer(clock, GameRules.PULSE_TIME,
            { true },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.Pulse()) })

    fun isInPlay() = (isGameOver or isPaused).not()

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
        save()
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

    private fun save() {
        gameDataManager.savedGame = SavedGame(
                clock = clock,
                score = score,
                isGameOver = isGameOver,
                doRestart = doRestart,
                isPaused = isPaused,
                backgroundBlockMap = backgroundBlockMap,
                currentPiece = currentPiece,
                nextPiece = nextPiece,
                reservePiece = reservePiece,
                hasSwappedReserve = hasSwappedReserve)
    }

    private fun exit() {
        Gdx.app.exit()
    }
}
