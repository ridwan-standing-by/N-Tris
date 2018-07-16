package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

abstract class View(val dimensions: Dimensions, val fonts: Fonts, val originBlocksX: Float, val originBlocksY: Float, val blocksWidth: Float, val blockHeight: Float) {

    val originX = dimensions.rescaledBlock() * originBlocksX
    val originY = dimensions.rescaledBlock() * originBlocksY
    val width = dimensions.rescaledBlock() * blocksWidth
    val height = dimensions.rescaledBlock() * blockHeight

    fun renderBorder(shapeRenderer: ShapeRenderer) {
        shapeRenderer.rect(originX, originY, width, height)
    }

    fun wasInputInView(x: Float, y: Float): Boolean = originX <= x && x < (originX + width)
            && originY <= y && y < originY + height

    abstract fun handleInputInView()
    abstract fun render(sb: SpriteBatch, shapeRenderer: ShapeRenderer)
}