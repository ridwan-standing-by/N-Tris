package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.Game

class EventHandler {

    val clock = Clock()
    private val eventQueue = mutableListOf<Event>()

    fun queue(event: Event) {
        eventQueue.add(event)
    }

    fun handleEvents(game: Game) {
        eventQueue.forEach { event ->
            event.block(game)
            eventQueue.remove(event)
        }
    }
}
