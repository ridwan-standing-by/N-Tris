package com.ridwanstandingby.ntris.render.views.score

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.SCORE_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class ScoreView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

    private val scaledPadding = dimensions.rescale(TEXT_PADDING)
    private val ySpacing = FontHelper.getDimensionsOfText(fonts.boxInfo, TEXT_SCORE.format(0F)).y + scaledPadding

    override val inputKeys = SCORE_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.score = true
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer, game: Game) {
        renderBorder(sr)
        renderScoreAndCharacters(sb, game)
    }

    private fun renderScoreAndCharacters(sb: SpriteBatch, game: Game) {
        renderText(sb, game)
        renderHighScoreIcon(sb)
        renderCurrentScoreIcon(sb)
    }

    private fun renderText(sb: SpriteBatch, game: Game) {
        val x = originX + scaledPadding
        val y = originY + height - scaledPadding
        fonts.boxInfo.draw(sb, TEXT_SCORE.format(game.score.points), x, y)
        fonts.boxInfo.draw(sb, TEXT_LINES.format(game.score.lines), x, y - 1.0f * ySpacing)
        fonts.boxInfo.draw(sb, TEXT_SCORE.format(game.highScore.points), x, y - 2.25f * ySpacing)
        fonts.boxInfo.draw(sb, TEXT_LINES.format(game.highScore.lines), x, y - 3.25f * ySpacing)
    }

    private fun renderHighScoreIcon(sb: SpriteBatch) {
        val x = originX + scaledPadding
        val y = originY + height - scaledPadding * 2f - 2.25f * ySpacing
        fonts.boxCharacter.draw(sb, HIGH_SCORE_ICON, x, y)
    }

    private fun renderCurrentScoreIcon(sb: SpriteBatch) {
        val x = originX + scaledPadding * 3/2
        val y = originY + height - scaledPadding * 2f
        fonts.boxCharacter.draw(sb, CURRENT_SCORE_ICON, x, y)
    }

    companion object {
        const val TEXT_PADDING = 16
        const val TEXT_SCORE = "  Score%1$9s"
        const val TEXT_LINES = "  Lines%1$9s"
        const val HIGH_SCORE_ICON = "★"
        const val CURRENT_SCORE_ICON = "▶"
    }
}
