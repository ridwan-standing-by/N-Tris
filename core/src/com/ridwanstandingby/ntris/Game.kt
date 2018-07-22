package com.ridwanstandingby.ntris

import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput

class Game {

    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(eventHandler)

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(rawPlayInput)
    }

    fun currentPieceMoveDown() {
        // TODO
    }

    fun currentPieceMoveLeft() {
        // TODO
    }

    fun currentPieceMoveRight() {
        // TODO
    }

    fun currentPieceRotateLeft() {
        // TODO
    }

    fun currentPieceRotateRight() {
        // TODO
    }

    fun togglePause() {
        // TODO
    }

    fun swapReserveAttempt() {
        // TODO
    }
}
