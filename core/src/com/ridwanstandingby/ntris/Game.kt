package com.ridwanstandingby.ntris

import com.badlogic.gdx.graphics.Color
import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.events.EventHandler
import com.ridwanstandingby.ntris.input.InputEventResolver
import com.ridwanstandingby.ntris.input.RawPlayInput
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

    var currentPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(4)][3], Color.CYAN, IntVector2(5, 30))
    var nextPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(8)][160], Color.CHARTREUSE, IntVector2(0, 0))
    var reservePiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(7)][98], Color.FIREBRICK, IntVector2(0, 0))
    var backgroundBlockMap = BlockMap().apply {
        blocks.add(Block(IntVector2(5, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(6, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 7), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 9), Color.GOLDENROD))
    }

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(rawPlayInput)
    }

    private fun tryCurrentPieceMove(move: Polyomino.() -> Unit) =
            legalMoveHelper.ifMoveIsLegalThenDoMove(currentPiece, backgroundBlockMap) { move() }

    fun currentPieceMoveDown() = tryCurrentPieceMove { moveDown() }

    fun currentPieceMoveLeft() = tryCurrentPieceMove { moveLeft() }

    fun currentPieceMoveRight() = tryCurrentPieceMove { moveRight() }

    fun currentPieceRotateLeft() = tryCurrentPieceMove { rotateLeft() }

    fun currentPieceRotateRight() = tryCurrentPieceMove { rotateRight() }

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
