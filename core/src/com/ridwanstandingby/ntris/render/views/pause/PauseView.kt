package com.ridwanstandingby.ntris.render.views.pause

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Colours
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class PauseView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2) :
    View(dimensions, fonts, originBlocks, sizeBlocks) {

    private val scaledPadding = dimensions.rescale(TEXT_PADDING)

    override val inputKeys = KeyInput.PAUSE_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {}

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        if (game.isPaused) {
            renderBackground(sr)
            renderBorder(sr)
        }
    }

    private fun renderBackground(sr: ShapeRenderer) {
        sr.set(ShapeRenderer.ShapeType.Filled)
        sr.rect(
            origin.x,
            origin.y,
            size.x,
            size.y,
            Colours.BACKGROUND_COLOUR,
            Colours.BACKGROUND_COLOUR,
            Colours.BACKGROUND_COLOUR,
            Colours.BACKGROUND_COLOUR
        )
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        if (game.isPaused) {
            renderPauseTitleText(sb)
        }
    }

    private fun renderPauseTitleText(sb: SpriteBatch) {
        val gameOverTitleSize = FontHelper.getDimensionsOfText(fonts.boxInfo, PAUSE_TITLE_TEXT)
        val x = origin.x + size.x / 2 - gameOverTitleSize.x / 2
        val y = origin.y + size.y - scaledPadding
        fonts.boxInfo.draw(sb, PAUSE_TITLE_TEXT, x, y)
    }

    companion object {
        const val TEXT_PADDING = 400
        const val PAUSE_TITLE_TEXT = "Paused"
    }
}
