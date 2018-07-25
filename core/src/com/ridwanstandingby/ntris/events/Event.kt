package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.game.Game

abstract class Event(val block: (game: Game) -> Unit) {
    var done = false
}
