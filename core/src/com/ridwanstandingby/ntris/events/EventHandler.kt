package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.Game

class EventHandler() {

    val clock = Clock()
    val eventQueue = mutableListOf<Event>()

    fun handleEvents(game: Game) {
        eventQueue.forEach { event ->
            if (clock.t >= event.t) {
                event.block(game)
                eventQueue.remove(event)
            }
        }
    }
}