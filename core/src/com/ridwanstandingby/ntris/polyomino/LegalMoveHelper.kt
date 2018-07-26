package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.game.GameRules
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

class LegalMoveHelper {

    fun ifMoveSequenceIsLegalThenDoMoves(piece: Polyomino, backgroundBlockMap: BlockMap, moves: Iterable<Polyomino.() -> Unit>): Boolean =
            if (isMoveSequenceLegal(piece, backgroundBlockMap, moves)) {
                piece.doMoves(moves)
                true
            } else {
                false
            }

    private fun isMoveSequenceLegal(piece: Polyomino, backgroundBlockMap: BlockMap, moves: Iterable<Polyomino.() -> Unit>): Boolean {
        val dummyPiece = piece.copy()
        dummyPiece.doMoves(moves)
        val futureBlockMap = combinePolyominoAndBlockMap(dummyPiece, backgroundBlockMap)
        return isLegalBlockMap(futureBlockMap)
    }

    private fun combinePolyominoAndBlockMap(polyomino: Polyomino, backgroundBlockMap: BlockMap): BlockMap =
            BlockMap(mutableListOf(
                    *polyomino.generateBlocks().toTypedArray(),
                    *backgroundBlockMap.blocks.toTypedArray()))

    private fun isLegalBlockMap(blockMap: BlockMap): Boolean =
            areBlocksNonColliding(blockMap) && areBlocksAllInBounds(blockMap)

    private fun areBlocksNonColliding(blockMap: BlockMap): Boolean {
        val listOfPositions = blockMap.blocks.map { block -> block.position }
        return listOfPositions == listOfPositions.distinct()
    }

    private fun areBlocksAllInBounds(blockMap: BlockMap): Boolean {
        blockMap.blocks.map { block -> block.position }
                .forEach { position ->
                    if (!positionIsInBounds(position)) return false
                }
        return true
    }

    private fun positionIsInBounds(position: IntVector2): Boolean =
            position.x >= 0 && position.x < GameRules.PLAY_BLOCK_SIZE.x && position.y >= 0

    private fun Polyomino.doMoves(moves: Iterable<Polyomino.() -> Unit>) =
            moves.forEach { move ->
                move()
            }
}
