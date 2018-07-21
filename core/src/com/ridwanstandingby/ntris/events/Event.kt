package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.Game

data class Event(val t: Float, val block: (game: Game) -> Unit)