package com.ridwanstandingby.ntris.input.debounce

abstract class Debouncer(protected val block: () -> Unit) {

    var nowPressed = false
    private var wasPressed = false

    private fun isNewlyPressed() = !wasPressed and nowPressed
    private fun isAlreadyPressed() = wasPressed and nowPressed

    fun invokeDebounced() {
        if (isNewlyPressed()) {
            handleInvokeNewlyPressed()
        }
        if (isAlreadyPressed()) {
            handleInvokeAlreadyPressed()
        }
        wasPressed = nowPressed
    }

    protected abstract fun handleInvokeNewlyPressed()
    protected abstract fun handleInvokeAlreadyPressed()
}