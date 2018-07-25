package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.GameRules
import com.ridwanstandingby.ntris.polyomino.geometry.Array2D

class BlockMap {

    val blocks = mutableListOf<Block>()
    val blockArray: Array2D<Block?>
        get() {
            val array = Array2D<Block?>(GameRules.PLAY_BLOCK_SIZE.x, GameRules.PLAY_BLOCK_SIZE.y)
            blocks.forEach { array[it.position] = it }
            return array
        }
}
