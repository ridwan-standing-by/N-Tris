package com.ridwanstandingby.ntris.input.debounce

abstract class Debouncer(protected val block: () -> Unit) {

    var nowPressed = false
    var wasPressed = true

    private fun isNewlyPressed() = !wasPressed and nowPressed
    private fun isAlreadyPressed() = wasPressed and nowPressed

    fun invokeDebounced() {
        if (isNewlyPressed()) {
            handleInvokeNewlyPressed()
        }
        if (isAlreadyPressed()) {
            handleInvokeAlreadyPressed()
        }
    }

    fun cycle() {
        wasPressed = nowPressed
    }

    protected abstract fun handleInvokeNewlyPressed()
    protected abstract fun handleInvokeAlreadyPressed()
}
