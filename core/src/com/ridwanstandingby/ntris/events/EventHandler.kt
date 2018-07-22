package com.ridwanstandingby.ntris.events

import com.ridwanstandingby.ntris.Game

class EventHandler {

    private val eventQueue = mutableListOf<Event>()

    fun queue(event: Event) {
        eventQueue.add(event)
    }

    fun handleEvents(game: Game) {
        eventQueue.filter { event -> !event.done }
                .forEach { event ->
                    event.block(game)
                    event.done = true
                }
        eventQueue.removeAll { event -> event.done }
    }
}
