package com.ridwanstandingby.ntris.render

class Dimensions(val width: Int, val height: Int) {

    val scale: Float
    val ratio: Float


    init {
        scale = height / gameHeight.toFloat()
        ratio = width.toFloat() / height.toFloat()
    }

    fun rescale(x: Int): Int {
        return (scale * x.toFloat()).toInt()
    }

    companion object {
        val gameWidth = 1920
        val gameHeight = 1024
    }
}