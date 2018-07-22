package com.ridwanstandingby.ntris.input.debounce

class SimpleDebouncer(private val block: () -> Unit) : Debouncer(block) {
    override fun handleInvokeNewlyPressed() = block()
    override fun handleInvokeAlreadyPressed() {}
}
