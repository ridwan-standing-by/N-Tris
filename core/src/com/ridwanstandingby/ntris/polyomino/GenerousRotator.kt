package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.game.GameRules

class GenerousRotator(private val ifLegalThenDo: (piece: Polyomino, moves: Iterable<Polyomino.() -> Unit>) -> Boolean) {

    fun tryPieceRotateLeft(piece: Polyomino): Boolean =
            tryPieceGenerousRotate(piece, { rotateLeft() }, { moveLeft() }, { moveRight() })

    fun tryPieceRotateRight(piece: Polyomino): Boolean =
            tryPieceGenerousRotate(piece, { rotateRight() }, { moveRight() }, { moveLeft() })

    private fun tryPieceGenerousRotate(piece: Polyomino,
                                       rotateMove: Polyomino.() -> Unit,
                                       movePrimaryDirection: Polyomino.() -> Unit,
                                       moveSecondaryDirection: Polyomino.() -> Unit): Boolean {
        (0..GameRules.GENEROUS_ROTATION_MAX_MOVES).forEach {
            val primaryMoveList = List(it) { movePrimaryDirection } + rotateMove
            val secondaryMoveList = List(it) { moveSecondaryDirection } + rotateMove

            if (ifLegalThenDo(piece, primaryMoveList)) return true
            if (ifLegalThenDo(piece, secondaryMoveList)) return true
        }
        return false
    }
}
