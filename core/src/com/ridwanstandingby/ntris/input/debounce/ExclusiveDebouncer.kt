package com.ridwanstandingby.ntris.input.debounce

class ExclusiveDebouncer(private val dependant: Debouncer, block: () -> Unit): Debouncer(block) {

    override fun handleInvokeNewlyPressed() {
        if (dependant.wasPressed.not()) {
            block()
        }
    }

    override fun handleInvokeAlreadyPressed() {}
}
