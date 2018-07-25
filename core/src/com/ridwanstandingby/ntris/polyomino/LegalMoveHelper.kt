package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.GameRules
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

class LegalMoveHelper {

    fun ifMoveIsLegalThenDoMove(currentPiece: Polyomino, backgroundBlockMap: BlockMap, move: Polyomino.() -> Unit) {
        if (isMoveLegal(currentPiece, backgroundBlockMap, move)) currentPiece.move()
    }

    private fun isMoveLegal(currentPiece: Polyomino, backgroundBlockMap: BlockMap, move: Polyomino.() -> Unit): Boolean {
        val dummyPiece = currentPiece.copy()
        dummyPiece.move()
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

}
