package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.game.GameRules
import com.ridwanstandingby.ntris.game.Score

class CompleteLineChecker {

    fun checkLinesAndIncreaseScoreIfNecessary(blockMap: BlockMap, score: Score) {
        val yCoordinatesOfCompleteLines = findYCoordsOfCompleteLines(blockMap)
        if (yCoordinatesOfCompleteLines.isNotEmpty()) {
            destroyLinesAndShiftBlocksDown(blockMap, yCoordinatesOfCompleteLines)
            score.incrementScore(yCoordinatesOfCompleteLines.size)
        }
    }

    private fun findYCoordsOfCompleteLines(blockMap: BlockMap): List<Int> {
        val allYCoords = IntArray(GameRules.PLAY_BLOCK_SIZE.y) { y ->
            blockMap.blocks.filter { block -> block.position.y == y }.size
        }
        val yCoordinatesOfCompleteLines = mutableListOf<Int>()
        allYCoords.forEachIndexed { index, numberOfBlocks ->
            if (numberOfBlocks >= GameRules.PLAY_BLOCK_SIZE.x) yCoordinatesOfCompleteLines.add(index)
        }
        return yCoordinatesOfCompleteLines
    }

    private fun destroyLinesAndShiftBlocksDown(blockMap: BlockMap, yCoords: List<Int>) {
        yCoords.sortedDescending().forEach { y ->
            blockMap.blocks.removeAll { block -> block.position.y == y }
            blockMap.blocks.forEach { block -> if (block.position.y > y) block.position.y -= 1 }
        }
    }
}
