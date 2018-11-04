package com.ridwanstandingby.ntris.input

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.Event
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.debounce.ExclusiveDebouncer
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_DOWN_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.SimpleDebouncer
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer

class InputEventResolver(clock: Clock, private val eventHandler: EventHandler) {

    private val moveDownDebouncer = TimedDebouncer(clock, MOVE_DOWN_DEBOUNCE_TIME,
            { input -> input.moveDown },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.CurrentPieceMoveDown()) })

    private val moveLeftDebouncer = TimedDebouncer(clock, MOVE_LEFT_DEBOUNCE_TIME,
            { input -> input.moveLeft },
            { input, game -> game.isInPlay() and (input.moveLeft and input.moveRight).not() },
            { eventHandler.queue(Event.CurrentPieceMoveLeft()) })

    private val moveRightDebouncer = TimedDebouncer(clock, MOVE_RIGHT_DEBOUNCE_TIME,
            { input -> input.moveRight },
            { input, game -> game.isInPlay() and (input.moveLeft and input.moveRight).not() },
            { eventHandler.queue(Event.CurrentPieceMoveRight()) })

    private val rotateLeftDebouncer = TimedDebouncer(clock, ROTATE_LEFT_DEBOUNCE_TIME,
            { input -> input.rotateLeft },
            { input, game -> game.isInPlay() and (input.rotateLeft and input.rotateRight).not() },
            { eventHandler.queue(Event.CurrentPieceRotateLeft()) })

    private val rotateRightDebouncer = TimedDebouncer(clock, ROTATE_RIGHT_DEBOUNCE_TIME,
            { input -> input.rotateRight },
            { input, game -> game.isInPlay() and (input.rotateLeft and input.rotateRight).not() },
            { eventHandler.queue(Event.CurrentPieceRotateRight()) })

    private val reserveDebouncer = SimpleDebouncer(
            { input -> input.reserve },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.SwapReserveAttempt()) })

    private val reflectDebouncer = SimpleDebouncer(
            { input -> input.reflect },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.CurrentPieceReflect()) })

    private val playDebouncer = SimpleDebouncer(
            { input -> input.play },
            { _, game -> game.isInPlay() },
            { eventHandler.queue(Event.Pause()) })

    private val resumeDebouncer = ExclusiveDebouncer(playDebouncer,
            { input -> input.pauseResume },
            { _, game -> game.isPaused },
            { eventHandler.queue(Event.Resume()) })

    private val restartDebouncer = ExclusiveDebouncer(playDebouncer,
            { input -> input.pauseRestart },
            { _, game -> game.isPaused },
            { eventHandler.queue(Event.RestartGame()) })

    private val gameOverDebouncer = SimpleDebouncer(
            { input -> input.gameOver },
            { _, game -> game.isGameOver },
            { eventHandler.queue(Event.RestartGame()) })

    private val backDebouncer = SimpleDebouncer(
            { input -> input.back },
            { _, _ -> true },
            { eventHandler.queue(Event.Back()) })

    private val debouncers = listOf(
            moveDownDebouncer,
            moveLeftDebouncer,
            moveRightDebouncer,
            rotateLeftDebouncer,
            rotateRightDebouncer,
            reserveDebouncer,
            reflectDebouncer,
            playDebouncer,
            resumeDebouncer,
            restartDebouncer,
            gameOverDebouncer,
            backDebouncer)

    fun resolveInput(game: Game, input: RawPlayInput) {
        debouncers.forEach { it.update(input) }
        debouncers.forEach { it.invokeDebounced(input, game) }
        debouncers.forEach { it.cycle() }
    }
}
