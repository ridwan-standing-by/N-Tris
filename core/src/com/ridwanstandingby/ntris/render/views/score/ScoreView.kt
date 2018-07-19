package com.ridwanstandingby.ntris.render.views.score

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class ScoreView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    var score: Int = 0
    var lines: Int = 0
    var highScore: Int = 0
    var highLines: Int = 0

    private val scaledPadding = dimensions.rescale(TEXT_PADDING)
    private val ySpacing = FontHelper.getDimensionsOfText(fonts.boxInfo, TEXT_SCORE.format(0F)).y + scaledPadding

    override fun handleInputInView() {}

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderBorder(sr)
        renderScoreAndCharacters(sb)
    }

    private fun renderScoreAndCharacters(sb: SpriteBatch) {
        renderText(sb)
        renderHighScoreIcon(sb)
        renderCurrentScoreIcon(sb)
    }

    private fun renderText(sb: SpriteBatch) {
        val x = originX + scaledPadding
        val y = originY + height - scaledPadding
        fonts.boxInfo.draw(sb, TEXT_SCORE.format(score), x, y)
        fonts.boxInfo.draw(sb, TEXT_LINES.format(lines), x, y - 1.0f * ySpacing)
        fonts.boxInfo.draw(sb, TEXT_SCORE.format(highScore), x, y - 2.25f * ySpacing)
        fonts.boxInfo.draw(sb, TEXT_LINES.format(highLines), x, y - 3.25f * ySpacing)
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
        const val TEXT_SCORE = "  Score%1$7s"
        const val TEXT_LINES = "  Lines%1$7s"
        const val HIGH_SCORE_ICON = "★"
        const val CURRENT_SCORE_ICON = "▶"
    }
}
