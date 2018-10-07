package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.game.GameRules

class GenerousPieceManipulator(private val ifLegalThenDo: (piece: Polyomino, moves: Iterable<Polyomino.() -> Unit>) -> Boolean) {

    fun tryPieceRotateLeft(piece: Polyomino): Boolean =
            tryPieceGenerousManipulate(piece, { rotateLeft() }, { moveLeft() }, { moveRight() })

    fun tryPieceRotateRight(piece: Polyomino): Boolean =
            tryPieceGenerousManipulate(piece, { rotateRight() }, { moveRight() }, { moveLeft() })

    fun tryPieceReflect(piece: Polyomino): Boolean =
            tryPieceGenerousManipulate(piece, { reflect() }, { moveLeft() }, { moveRight() })

    private fun tryPieceGenerousManipulate(piece: Polyomino,
                                           manipulateMove: Polyomino.() -> Unit,
                                           movePrimaryDirection: Polyomino.() -> Unit,
                                           moveSecondaryDirection: Polyomino.() -> Unit): Boolean {
        (0..GameRules.GENEROUS_MANIPULATION_MAX_MOVES).forEach {
            val primaryMoveList = List(it) { movePrimaryDirection } + manipulateMove
            val secondaryMoveList = List(it) { moveSecondaryDirection } + manipulateMove

            if (ifLegalThenDo(piece, primaryMoveList)) return true
            if (ifLegalThenDo(piece, secondaryMoveList)) return true
        }
        return false
    }
}
