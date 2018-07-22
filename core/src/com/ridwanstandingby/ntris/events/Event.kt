package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.Game

abstract class Event(val block: (game: Game) -> Unit)
