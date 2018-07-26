package com.ridwanstandingby.ntris.game

import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2

object GameRules {

    val PLAY_BLOCK_SIZE = IntVector2(10, 30)
    const val PULSE_TIME = 0.8f
    const val GENEROUS_ROTATION_MAX_MOVES = 2
    val linesToScore = { lines: Int -> 5 * lines * (lines + 1) }
}
