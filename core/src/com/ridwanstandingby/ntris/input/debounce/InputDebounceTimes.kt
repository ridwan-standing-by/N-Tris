package com.ridwanstandingby.ntris.input.debounce

object InputDebounceTimes {
    private const val DEBOUNCE_TIME = 0.25F

    private const val MOVE_DEBOUNCE_TIME = DEBOUNCE_TIME
    private const val ROTATE_DEBOUNCE_TIME = DEBOUNCE_TIME

    const val MOVE_DOWN_DEBOUNCE_TIME = MOVE_DEBOUNCE_TIME
    const val MOVE_LEFT_DEBOUNCE_TIME = MOVE_DEBOUNCE_TIME
    const val MOVE_RIGHT_DEBOUNCE_TIME = MOVE_DEBOUNCE_TIME
    const val ROTATE_LEFT_DEBOUNCE_TIME = ROTATE_DEBOUNCE_TIME
    const val ROTATE_RIGHT_DEBOUNCE_TIME = ROTATE_DEBOUNCE_TIME
}