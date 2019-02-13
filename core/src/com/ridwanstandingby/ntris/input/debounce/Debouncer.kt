package com.ridwanstandingby.ntris.input.debounce

import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput

abstract class Debouncer(private val updateNowPressed: (input: RawPlayInput) -> Boolean,
                         private val doInvokeCondition: (input: RawPlayInput, game: Game) -> Boolean,
                         protected val block: () -> Unit) {

    private var nowPressed = false
    var wasPressed = true

    private fun isNewlyPressed() = !wasPressed and nowPressed
    private fun isAlreadyPressed() = wasPressed and nowPressed

    fun update(input: RawPlayInput) {
        nowPressed = updateNowPressed(input)
    }

    fun invokeDebounced(input: RawPlayInput, game: Game) {
        if (doInvokeCondition(input, game)) {
            if (isNewlyPressed()) {
                handleInvokeNewlyPressed()
            }
            if (isAlreadyPressed()) {
                handleInvokeAlreadyPressed()
            }
        }
    }

    fun cycle() {
        wasPressed = nowPressed
    }

    protected abstract fun handleInvokeNewlyPressed()
    protected abstract fun handleInvokeAlreadyPressed()
}
