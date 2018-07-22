package com.ridwanstandingby.ntris

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader

class Game {

    private val clock = Clock()
    private val eventHandler = EventHandler()
    private val inputEventResolver = InputEventResolver(clock, eventHandler)
    private val polyominoBlueprintHolder = PolyominoBlueprintLoader().load()

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(rawPlayInput)
    }

    fun currentPieceMoveDown() {
        System.out.println("MOVE DOWN")
    }

    fun currentPieceMoveLeft() {
        System.out.println("MOVE LEFT")
    }

    fun currentPieceMoveRight() {
        System.out.println("MOVE RIGHT")
    }

    fun currentPieceRotateLeft() {
        System.out.println("ROTATE LEFT")
    }

    fun currentPieceRotateRight() {
        System.out.println("ROTATE RIGHT")
    }

    fun togglePause() {
        System.out.println("TOGGLE PAUSE")
    }

    fun swapReserveAttempt() {
        System.out.println("SWAP RESERVE ATTEMPT")
    }

    fun update(dt: Float) {
        eventHandler.handleEvents(this)
        clock.tick(dt)
    }
}
