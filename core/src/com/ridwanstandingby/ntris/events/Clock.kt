package com.ridwanstandingby.ntris.events

class Clock {

    var t: Float = 0F

    fun tick(dt: Float) {
        t += dt
    }
}
