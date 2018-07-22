package com.ridwanstandingby.ntris.input.debounce

import com.ridwanstandingby.ntris.events.Clock

class TimedDebouncer(private val clock: Clock,
                     private val debounceTime: Float,
                     private val block: () -> Unit) : Debouncer(block) {

    private var lastInvoked: Float = 0F

    override fun handleInvokeNewlyPressed() = invokeAndSaveInvokeTime()

    override fun handleInvokeAlreadyPressed() {
        if (debounceTimeHasElapsed()) invokeAndSaveInvokeTime()
    }

    private fun debounceTimeHasElapsed() = clock.t >= lastInvoked + debounceTime

    private fun invokeAndSaveInvokeTime() {
        lastInvoked = clock.t
        block()
    }
}
