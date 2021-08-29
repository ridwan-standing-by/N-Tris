package com.ridwanstandingby.ntris.render

import com.badlogic.gdx.math.Vector2
import kotlin.math.min

class Dimensions(val screenWidth: Int, val screenHeight: Int) {

    private val scale: Float

    private val gameWidth = 1140 // 19 blocks
    private val gameHeight = 1920 // 32 blocks

    val block = 60f

    val extraBlocks: Vector2

    init {
        val scaleX = screenWidth / gameWidth.toFloat()
        val scaleY = screenHeight / gameHeight.toFloat()

        scale = min(scaleX, scaleY)

        extraBlocks = Vector2(
            calculateExtraBlocks(screenWidth, gameWidth),
            calculateExtraBlocks(screenHeight, gameHeight)
        )
    }

    private fun calculateExtraBlocks(screenPixels: Int, gameDim: Int): Float {
        val gamePixels = screenPixels.toFloat() - rescale(gameDim)
        return gamePixels / rescaledBlock()
    }

    fun rescale(x: Int): Float = (scale * x.toFloat())
    fun rescaledBlock() = block * scale
}
