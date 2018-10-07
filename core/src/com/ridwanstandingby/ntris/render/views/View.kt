package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

abstract class View(val dimensions: Dimensions, val fonts: Fonts, originBlocksX: Float, originBlocksY: Float, val blocksWidth: Float, val blocksHeight: Float) {

    val originX = dimensions.rescaledBlock() * originBlocksX
    val originY = dimensions.rescaledBlock() * originBlocksY
    val width = dimensions.rescaledBlock() * blocksWidth
    val height = dimensions.rescaledBlock() * blocksHeight

    var queueHighlight: Boolean = false

    fun renderBorder(sr: ShapeRenderer) {
        sr.set(ShapeRenderer.ShapeType.Line)
        sr.rect(originX, originY, width, height)
    }

    fun renderHighlightIfQueued(sr: ShapeRenderer) {
        if (queueHighlight) {
            sr.set(ShapeRenderer.ShapeType.Filled)
            sr.rect(originX, originY, width, height, HIGHLIGHT_COLOUR, HIGHLIGHT_COLOUR, HIGHLIGHT_COLOUR, HIGHLIGHT_COLOUR)
            queueHighlight = false
        }
    }

    fun wasPointerInView(x: Float, y: Float): Boolean = originX <= x && x < (originX + width)
            && originY <= y && y < originY + height

    abstract val inputKeys: List<Int>
    abstract fun handleInputIsInView(rawPlayInput: RawPlayInput)
    open fun renderBlocks(sb: SpriteBatch, game: Game) {}
    open fun renderShapes(sr: ShapeRenderer, game: Game) {}
    open fun renderSprites(sb: SpriteBatch, game: Game) {}

    companion object {
        private val HIGHLIGHT_COLOUR = Color(0x297577FF)
    }
}
