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

    var currentPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(4)][3], IntVector2(5, 30), Color.CYAN)
    var nextPiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(8)][160], IntVector2(0, 0), Color.CHARTREUSE)
    var reservePiece = Polyomino(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(7)][98], IntVector2(0, 0), Color.FIREBRICK)
    var backgroundBlockMap = BlockMap().apply {
        blocks.add(Block(IntVector2(5, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(6, 8), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 7), Color.GOLDENROD))
        blocks.add(Block(IntVector2(5, 9), Color.GOLDENROD))
    }

    fun resolvePlayInput(rawPlayInput: RawPlayInput) {
        inputEventResolver.resolveInput(rawPlayInput)
    }

    fun currentPieceMoveDown() {
        legalMoveHelper.ifMoveIsLegalThenDoMove(currentPiece, backgroundBlockMap) {
            moveDown()
        }
    }

    fun currentPieceMoveLeft() {
        legalMoveHelper.ifMoveIsLegalThenDoMove(currentPiece, backgroundBlockMap) {
            moveLeft()
        }
    }

    fun currentPieceMoveRight() {
        legalMoveHelper.ifMoveIsLegalThenDoMove(currentPiece, backgroundBlockMap) {
            moveRight()
        }
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
