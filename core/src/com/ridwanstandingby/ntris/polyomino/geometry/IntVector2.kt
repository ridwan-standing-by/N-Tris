package com.ridwanstandingby.ntris.polyomino.geometry

import kotlin.math.floor

data class IntVector2(var x: Int, var y: Int) {

    constructor(x: Float, y: Float): this(floorToInt(x), floorToInt(y))

    operator fun unaryPlus() = IntVector2(x, y)
    operator fun unaryMinus() = IntVector2(-x, -y)

    operator fun plus(other: IntVector2) = IntVector2(x + other.x, y + other.y)
    operator fun minus(other: IntVector2) = IntVector2(x - other.x, y - other.y)

    operator fun times(scale: Int): IntVector2 = IntVector2(x * scale, y * scale)
    operator fun times(scale: Float): IntVector2 = IntVector2(floorToInt(x * scale), floorToInt(y * scale))

    operator fun div(scale: Float) = this * (1 / scale)
    operator fun div(scale: Int) = this / scale.toFloat()

    companion object {
        private fun floorToInt(f: Float) = floor(f).toInt()
    }
}
