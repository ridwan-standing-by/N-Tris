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

class PauseResumeView(
    dimensions: Dimensions,
    fonts: Fonts,
    originBlocks: Vector2,
    sizeBlocks: Vector2
) : View(dimensions, fonts, originBlocks, sizeBlocks) {

    override val inputKeys: List<Int> = KeyInput.PAUSE_RESUME_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.pauseResume = true
    }

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
            renderResumeIcon(sb)
        }
    }

    private fun renderResumeIcon(sb: SpriteBatch) {
        val resumeIconSize = FontHelper.getDimensionsOfText(fonts.buttonCharacter, RESUME_ICON)
        val x = origin.x + size.x / 2 - resumeIconSize.x / 2
        val y = origin.y + size.y / 2 + resumeIconSize.y / 2
        fonts.buttonCharacter.draw(sb, RESUME_ICON, x, y)
    }

    companion object {
        const val RESUME_ICON = "▶"
    }
}
