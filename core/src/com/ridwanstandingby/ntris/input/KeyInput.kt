package com.ridwanstandingby.ntris.input

import com.badlogic.gdx.Input

object KeyInput {
    val MOVE_DOWN_KEYS = listOf(Input.Keys.S, Input.Keys.DOWN)
    val MOVE_LEFT_KEYS = listOf(Input.Keys.A, Input.Keys.LEFT)
    val MOVE_RIGHT_KEYS = listOf(Input.Keys.D, Input.Keys.RIGHT)
    val ROTATE_LEFT_KEYS = listOf(Input.Keys.Q, Input.Keys.W, Input.Keys.UP)
    val ROTATE_RIGHT_KEYS = listOf(Input.Keys.E)
    val REFLECT_KEYS = listOf(Input.Keys.TAB)
    val RESERVE_KEYS = listOf(Input.Keys.SHIFT_LEFT, Input.Keys.SHIFT_RIGHT)
    val SCORE_KEYS = listOf<Int>()
    val NEXT_KEYS = listOf(Input.Keys.CONTROL_LEFT, Input.Keys.CONTROL_RIGHT)
    val PLAY_KEYS = listOf(Input.Keys.P)
    val PAUSE_KEYS = listOf<Int>()
    val PAUSE_RESUME_KEYS = listOf(Input.Keys.P)
    val PAUSE_RESTART_KEYS = listOf(Input.Keys.R)
    val GAME_OVER_KEYS = listOf(Input.Keys.R)
    val BACK_KEYS = listOf(Input.Keys.ESCAPE, Input.Keys.BACK)
}
