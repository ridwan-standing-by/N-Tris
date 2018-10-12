package com.ridwanstandingby.ntris.render

import kotlin.math.min

class Dimensions(val screenWidth: Int, val screenHeight: Int) {

    private val scale : Float

    private val gameWidth = 1140 // 19 blocks
    private val gameHeight = 1920 // 32 blocks

    val block = 60f


    init {
        val scaleX = screenWidth / gameWidth.toFloat()
        val scaleY = screenHeight / gameHeight.toFloat()

        scale = min(scaleX, scaleY)
    }

    fun rescale(x: Int): Float = (scale * x.toFloat())
    fun rescaledBlock() = block * scale
}
