package com.ridwanstandingby.ntris.render.views.gameOver

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Colours.BACKGROUND_COLOUR
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class GameOverView(
    dimensions: Dimensions,
    fonts: Fonts,
    originBlocks: Vector2,
    sizeBlocks: Vector2
) : View(dimensions, fonts, originBlocks, sizeBlocks) {

    private val scaledPadding = dimensions.rescale(TEXT_PADDING)

    override val inputKeys: List<Int> = KeyInput.GAME_OVER_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.gameOver = true
    }

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        if (game.isGameOver) {
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
            BACKGROUND_COLOUR,
            BACKGROUND_COLOUR,
            BACKGROUND_COLOUR,
            BACKGROUND_COLOUR
        )
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        if (game.isGameOver) {
            renderGameOverTitleText(sb)
            renderRestartIcon(sb)
        }
    }

    private fun renderGameOverTitleText(sb: SpriteBatch) {
        val gameOverTitleSize = FontHelper.getDimensionsOfText(fonts.boxInfo, GAME_OVER_TITLE)
        val x = origin.x + size.x / 2 - gameOverTitleSize.x / 2
        val y = origin.y + size.y - scaledPadding
        fonts.boxInfo.draw(sb, GAME_OVER_TITLE, x, y)
    }

    private fun renderRestartIcon(sb: SpriteBatch) {
        val restartIconSize = FontHelper.getDimensionsOfText(fonts.buttonCharacter, RESTART_ICON)
        val x = origin.x + size.x / 2 - restartIconSize.x / 2
        val y = origin.y + scaledPadding + restartIconSize.y
        fonts.buttonCharacter.draw(sb, RESTART_ICON, x, y)
    }

    companion object {
        const val TEXT_PADDING = 96
        const val GAME_OVER_TITLE = "Game Over!"
        const val RESTART_ICON = "⟳"
    }
}
