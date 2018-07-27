package com.ridwanstandingby.ntris.input

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.events.Events
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_DOWN_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.MOVE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_LEFT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.InputDebounceTimes.ROTATE_RIGHT_DEBOUNCE_TIME
import com.ridwanstandingby.ntris.input.debounce.SimpleDebouncer
import com.ridwanstandingby.ntris.input.debounce.TimedDebouncer

class InputEventResolver(clock: Clock, private val eventHandler: EventHandler) {

    private val moveDownDebouncer = TimedDebouncer(clock, MOVE_DOWN_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveDown())
    }
    private val moveLeftDebouncer = TimedDebouncer(clock, MOVE_LEFT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveLeft())
    }
    private val moveRightDebouncer = TimedDebouncer(clock, MOVE_RIGHT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceMoveRight())
    }
    private val rotateLeftDebouncer = TimedDebouncer(clock, ROTATE_LEFT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceRotateLeft())
    }
    private val rotateRightDebouncer = TimedDebouncer(clock, ROTATE_RIGHT_DEBOUNCE_TIME) {
        eventHandler.queue(Events.CurrentPieceRotateRight())
    }
    private val reserveDebouncer = SimpleDebouncer {
        eventHandler.queue(Events.SwapReserveAttempt())
    }
    private val nextDebouncer = SimpleDebouncer {
        eventHandler.queue(Events.NextRerollAttempt())
    }
    private val pauseDebouncer = SimpleDebouncer {
        eventHandler.queue(Events.Pause())
    }

    private val exitDebouncer = SimpleDebouncer {
        eventHandler.queue((Events.Exit()))
    }

    fun resolveInput(input: RawPlayInput) {
        updateDebouncers(input)
        resolveExitInput()
        resolveMovementInput(input)
        resolveRotationInput(input)
        resolveReserveInput()
        resolveNextInput()
        resolvePauseInput()
    }

    private fun updateDebouncers(input: RawPlayInput) {
        exitDebouncer.nowPressed = input.exit
        moveDownDebouncer.nowPressed = input.moveDown
        moveLeftDebouncer.nowPressed = input.moveLeft
        moveRightDebouncer.nowPressed = input.moveRight
        rotateLeftDebouncer.nowPressed = input.rotateLeft
        rotateRightDebouncer.nowPressed = input.rotateRight
        reserveDebouncer.nowPressed = input.reserve
        nextDebouncer.nowPressed = input.next
        pauseDebouncer.nowPressed = input.pause
    }

    private fun resolveExitInput() {
        exitDebouncer.invokeDebounced()
    }

    private fun resolveMovementInput(input: RawPlayInput) {
        if ((input.moveLeft and input.moveRight).not()) {
            moveLeftDebouncer.invokeDebounced()
            moveRightDebouncer.invokeDebounced()
        }
        moveDownDebouncer.invokeDebounced()
    }

    private fun resolveRotationInput(input: RawPlayInput) {
        if ((input.rotateLeft and input.rotateRight).not()) {
            rotateLeftDebouncer.invokeDebounced()
            rotateRightDebouncer.invokeDebounced()
        }
    }

    private fun resolveReserveInput() {
        reserveDebouncer.invokeDebounced()
    }

    private fun resolveNextInput() {
        nextDebouncer.invokeDebounced()
    }

    private fun resolvePauseInput() {
        pauseDebouncer.invokeDebounced()
    }
}
