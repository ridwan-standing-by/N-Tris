package com.ridwanstandingby.ntris.polyomino

import com.ridwanstandingby.ntris.GameRules
import com.ridwanstandingby.ntris.polyomino.geometry.Array2D

class BlockMap {

    val blocks = mutableSetOf<Block>()
    val blockArray = Array2D<Block?>(GameRules.PLAY_BLOCK_SIZE.x, GameRules.PLAY_BLOCK_SIZE.y)
}
