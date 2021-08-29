package com.ridwanstandingby.ntris.input.debounce

import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput

class ExclusiveDebouncer(
    private val dependant: Debouncer,
    updateNowPressed: (input: RawPlayInput) -> Boolean,
    doInvokeCondition: (input: RawPlayInput, game: Game) -> Boolean,
    block: () -> Unit
) : Debouncer(updateNowPressed, doInvokeCondition, block) {

    override fun handleInvokeNewlyPressed() {
        if (dependant.wasPressed.not()) {
            block()
        }
    }

    override fun handleInvokeAlreadyPressed() {}
}
