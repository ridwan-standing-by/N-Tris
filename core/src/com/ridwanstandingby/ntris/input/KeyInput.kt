package com.ridwanstandingby.ntris.input

import com.badlogic.gdx.Input

object KeyInput {
    val MOVE_DOWN_KEYS = listOf(Input.Keys.S, Input.Keys.DOWN)
    val MOVE_LEFT_KEYS = listOf(Input.Keys.A, Input.Keys.LEFT)
    val MOVE_RIGHT_KEYS = listOf(Input.Keys.D, Input.Keys.RIGHT)
    val ROTATE_LEFT_KEYS = listOf(Input.Keys.Q, Input.Keys.W, Input.Keys.UP)
    val ROTATE_RIGHT_KEYS = listOf(Input.Keys.E)
    val PAUSE_KEYS = listOf(Input.Keys.P)
    val RESERVE_KEYS = listOf(Input.Keys.SHIFT_LEFT, Input.Keys.SHIFT_RIGHT)
    val SCORE_KEYS = listOf<Int>()
    val NEXT_KEYS = listOf(Input.Keys.CONTROL_RIGHT, Input.Keys.TAB)
    val PLAY_KEYS = listOf<Int>()
    val EXIT_KEYS = listOf(Input.Keys.ESCAPE)
}
