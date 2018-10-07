package com.ridwanstandingby.ntris.render.views.gameOver

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class GameOverView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

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
        sr.rect(originX, originY, width, height, GAME_OVER_BG_COLOUR, GAME_OVER_BG_COLOUR, GAME_OVER_BG_COLOUR, GAME_OVER_BG_COLOUR)
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        if (game.isGameOver) {
            renderGameOverTitleText(sb)
            renderRestartIcon(sb)
        }
    }

    private fun renderGameOverTitleText(sb: SpriteBatch) {
        val gameOverTitleSize = FontHelper.getDimensionsOfText(fonts.boxInfo, GAME_OVER_TITLE)
        val x = originX + width/2 - gameOverTitleSize.x / 2
        val y = originY + height - scaledPadding
        fonts.boxInfo.draw(sb, GAME_OVER_TITLE, x, y)
    }

    private fun renderRestartIcon(sb: SpriteBatch) {
        val restartIconSize = FontHelper.getDimensionsOfText(fonts.buttonCharacter, RESTART_ICON)
        val x = originX + width/2 - restartIconSize.x / 2
        val y = originY + scaledPadding + restartIconSize.y
        fonts.buttonCharacter.draw(sb, RESTART_ICON, x ,y)
    }

    companion object {
        private val GAME_OVER_BG_COLOUR = Color(0x000000FF)

        const val TEXT_PADDING = 96
        const val GAME_OVER_TITLE = "Game Over!"
        const val RESTART_ICON = "⟳"
    }
}