package com.ridwanstandingby.ntris.input.debounce

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput

class TimedDebouncer(
    private val clock: Clock,
    private val debounceTime: Float,
    updateNowPressed: (input: RawPlayInput) -> Boolean,
    doInvokeCondition: (input: RawPlayInput, game: Game) -> Boolean,
    block: () -> Unit
) : Debouncer(updateNowPressed, doInvokeCondition, block) {

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
