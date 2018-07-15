package com.ridwanstandingby.ntris.render

class Dimensions(val width: Int, val height: Int) {

    val scaleX: Float
    val scaleY: Float

    val gameWidth = 1080
    val gameHeight = 1920

    val block = 60f
        get() = rescale(field)


    init {
        scaleX = width / gameWidth.toFloat()
        scaleY = height / gameHeight.toFloat()
    }

    fun rescale(x: Int): Float = (scaleX * x.toFloat())
    fun rescale(x: Float): Float = scaleX * x
}