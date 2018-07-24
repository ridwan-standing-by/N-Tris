package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.Game
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

abstract class View(val dimensions: Dimensions, val fonts: Fonts, val originBlocksX: Float, val originBlocksY: Float, val blocksWidth: Float, val blocksHeight: Float) {

    val originX = dimensions.rescaledBlock() * originBlocksX
    val originY = dimensions.rescaledBlock() * originBlocksY
    val width = dimensions.rescaledBlock() * blocksWidth
    val height = dimensions.rescaledBlock() * blocksHeight

    fun renderBorder(sr: ShapeRenderer) {
        sr.rect(originX, originY, width, height)
    }

    fun wasPointerInView(x: Float, y: Float): Boolean = originX <= x && x < (originX + width)
            && originY <= y && y < originY + height

    abstract val inputKeys: List<Int>
    abstract fun handleInputIsInView(rawPlayInput: RawPlayInput)
    abstract fun render(sb: SpriteBatch, sr: ShapeRenderer, game: Game)
}
