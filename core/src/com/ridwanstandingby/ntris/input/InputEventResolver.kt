package com.ridwanstandingby.ntris.input

import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.events.Events
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_DOWN_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.SimpleDebouncer
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer

class InputEventResolver(private val eventHandler: EventHandler) {

    private val moveDownDebouncer = TimedDebouncer(eventHandler.clock, MOVE_DOWN_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveDown())
    }
    private val moveLeftDebouncer = TimedDebouncer(eventHandler.clock, MOVE_LEFT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveLeft())
    }
    private val moveRightDebouncer = TimedDebouncer(eventHandler.clock, MOVE_RIGHT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveRight())
    }
    private val rotateLeftDebouncer = TimedDebouncer(eventHandler.clock, ROTATE_LEFT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceRotateLeft())
    }
    private val rotateRightDebouncer = TimedDebouncer(eventHandler.clock, ROTATE_RIGHT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceRotateRight())
    }
    private val reserveDebouncer = SimpleDebouncer {
        eventHandler.queue(Events.SwapReserveAttempt())
    }
    private val pauseDebouncer = SimpleDebouncer {
        eventHandler.queue(Events.Pause())
    }

    fun resolveInput(input: RawPlayInput) {
        updateDebouncers(input)
        resolveMovementInput(input)
        resolveRotationInput(input)
        resolveReserveInput(input)
        resolvePauseInput(input)
    }

    private fun updateDebouncers(input: RawPlayInput) {
        moveDownDebouncer.nowPressed = input.moveDown
        moveLeftDebouncer.nowPressed = input.moveLeft
        moveRightDebouncer.nowPressed = input.moveRight
        rotateLeftDebouncer.nowPressed = input.rotateLeft
        rotateRightDebouncer.nowPressed = input.rotateRight
        reserveDebouncer.nowPressed = input.reserve
        pauseDebouncer.nowPressed = input.pause
    }

    private fun resolveMovementInput(input: RawPlayInput) {
        if ((input.moveLeft and input.moveRight).not()) {
            if (input.moveLeft) moveLeftDebouncer.invokeDebounced()
            if (input.moveRight) moveRightDebouncer.invokeDebounced()
        }
        if (input.moveDown) moveDownDebouncer.invokeDebounced()
    }

    private fun resolveRotationInput(input: RawPlayInput) {
        if ((input.rotateLeft and input.rotateRight).not()) {
            if (input.rotateLeft) rotateLeftDebouncer.invokeDebounced()
            if (input.rotateRight) rotateRightDebouncer.invokeDebounced()
        }
    }

    private fun resolveReserveInput(input: RawPlayInput) {
        if (input.reserve) reserveDebouncer.invokeDebounced()
    }

    private fun resolvePauseInput(input: RawPlayInput) {
        if (input.pause) pauseDebouncer.invokeDebounced()
    }
}
