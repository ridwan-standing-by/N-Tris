package com.ridwanstandingby.ntris.input.debounce

class SimpleDebouncer(block: () -> Unit) : Debouncer(block) {
    override fun handleInvokeNewlyPressed() = block()
    override fun handleInvokeAlreadyPressed() {}
}
