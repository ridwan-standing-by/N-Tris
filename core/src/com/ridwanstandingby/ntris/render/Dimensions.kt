package com.ridwanstandingby.ntris.render

import kotlin.math.min

class Dimensions(val width: Int, val height: Int) {

    val scaleX: Float
    val scaleY: Float

    val scale : Float

    val gameWidth = 1080
    val gameHeight = 1920

    val block = 60f


    init {
        scaleX = width / gameWidth.toFloat()
        scaleY = height / gameHeight.toFloat()

        scale = min(scaleX, scaleY)
    }

    fun rescale(x: Int): Float = (scaleX * x.toFloat())
    fun rescale(x: Float): Float = scaleX * x

    fun rescaledBlock() = block * scale
}