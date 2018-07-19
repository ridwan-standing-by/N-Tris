package com.ridwanstandingby.ntris.render

import kotlin.math.min

class Dimensions(val width: Int, val height: Int) {

    val scale : Float

    val gameWidth = 1080
    val gameHeight = 1920

    val block = 60f


    init {
        val scaleX = width / gameWidth.toFloat()
        val scaleY = height / gameHeight.toFloat()

        scale = min(scaleX, scaleY)
    }

    fun rescale(x: Int): Float = (scale * x.toFloat())
    fun rescale(x: Float): Float = scale * x

    fun rescaledBlock() = block * scale
}
