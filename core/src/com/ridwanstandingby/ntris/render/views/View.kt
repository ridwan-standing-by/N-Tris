package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Colours.HIGHLIGHT_COLOUR
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import ktx.math.times

abstract class View(
    val dimensions: Dimensions,
    val fonts: Fonts,
    originBlocks: Vector2,
    val sizeBlocks: Vector2
) {

    val origin = originBlocks * dimensions.rescaledBlock()
    val size = sizeBlocks * dimensions.rescaledBlock()

    var queueHighlight: Boolean = false

    fun renderBorder(sr: ShapeRenderer) {
        sr.set(ShapeRenderer.ShapeType.Line)
        sr.rect(origin.x, origin.y, size.x, size.y)
    }

    fun renderHighlightIfQueued(sr: ShapeRenderer) {
        if (queueHighlight) {
            sr.set(ShapeRenderer.ShapeType.Filled)
            sr.rect(
                origin.x,
                origin.y,
                size.x,
                size.y,
                HIGHLIGHT_COLOUR,
                HIGHLIGHT_COLOUR,
                HIGHLIGHT_COLOUR,
                HIGHLIGHT_COLOUR
            )
            queueHighlight = false
        }
    }

    open fun wasPointerInView(x: Float, y: Float): Boolean =
        origin.x <= x && x < (origin.x + size.x)
                && origin.y <= y && y < origin.y + size.y

    abstract val inputKeys: List<Int>
    abstract fun handleInputIsInView(rawPlayInput: RawPlayInput)
    open fun renderBlocks(sb: SpriteBatch, game: Game) {}
    open fun renderShapes(sr: ShapeRenderer, game: Game) {}
    open fun renderSprites(sb: SpriteBatch, game: Game) {}
}
