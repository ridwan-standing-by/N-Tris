package com.ridwanstandingby.ntris.render

import kotlin.math.min

class Dimensions(val screenWidth: Int, val screenHeight: Int) {

    private val scale : Float

    private val gameWidth = 1080
    private val gameHeight = 1920

    val block = 60f


    init {
        val scaleX = screenWidth / gameWidth.toFloat()
        val scaleY = screenHeight / gameHeight.toFloat()

        scale = min(scaleX, scaleY)
    }

    fun rescale(x: Int): Float = (scale * x.toFloat())
    fun rescale(x: Float): Float = scale * x
    fun rescaledBlock() = block * scale
}
