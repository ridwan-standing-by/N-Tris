package com.ridwanstandingby.ntris.input.debounce

import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput

class SimpleDebouncer(updateNowPressed: (input: RawPlayInput) -> Boolean,
                      doInvokeCondition: (input: RawPlayInput, game: Game) -> Boolean,
                      block: () -> Unit): Debouncer(updateNowPressed, doInvokeCondition, block) {
    override fun handleInvokeNewlyPressed() = block()
    override fun handleInvokeAlreadyPressed() {}
}
