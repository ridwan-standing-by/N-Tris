package com.ridwanstandingby.ntris.polyomino.geometry

import com.badlogic.gdx.math.Vector2
import kotlin.math.floor

data class IntVector2(var x: Int, var y: Int) {

    constructor(v: IntVector2): this(v.x, v.y)
    constructor(v: Vector2): this(floorToInt(v.x), floorToInt(v.y))
    constructor(x: Float, y: Float): this(floorToInt(x), floorToInt(y))

    operator fun unaryPlus() = IntVector2(x, y)
    operator fun unaryMinus() = IntVector2(-x, -y)

    operator fun plus(other: IntVector2) = IntVector2(x + other.x, y + other.y)
    operator fun minus(other: IntVector2) = IntVector2(x - other.x, y - other.y)

    operator fun plus(other: Vector2) = Vector2(x.toFloat() + other.x, y.toFloat() + other.y)
    operator fun minus(other: Vector2) = Vector2(x.toFloat() - other.x, y.toFloat() - other.y)

    operator fun times(scale: Int): IntVector2 = IntVector2(x * scale, y * scale)
    operator fun times(scale: Float): IntVector2 = IntVector2(floorToInt(x * scale), floorToInt(y * scale))

    operator fun div(scale: Float) = this * (1 / scale)
    operator fun div(scale: Int) = this / scale.toFloat()

    companion object {
        private fun floorToInt(f: Float) = floor(f).toInt()
    }
}
