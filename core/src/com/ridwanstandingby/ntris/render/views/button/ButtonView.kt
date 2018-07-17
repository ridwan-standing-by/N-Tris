package com.ridwanstandingby.ntris.render.views.button

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

abstract class ButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float, private val icon: String) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderBorder(sr)
        renderTextInCentre(sb, fonts.buttonCharacter, icon)
    }

    private fun renderTextInCentre(sb: SpriteBatch, font: BitmapFont, text: String) {
        val textDims = FontHelper.getDimensionsOfText(font, text)
        val x = originX + width/2 - textDims.x/2
        val y = originY + height/2 + textDims.y/2
        font.draw(sb, text, x, y)
    }

    companion object {
        const val MOVE_DOWN_BUTTON_ICON = "▼"
        const val MOVE_LEFT_BUTTON_ICON = "◀"
        const val MOVE_RIGHT_BUTTON_ICON = "▶"
        const val ROTATE_LEFT_BUTTON_ICON = "↶"
        const val ROTATE_RIGHT_BUTTON_ICON = "↷"
        const val PAUSE_BUTTON_ICON = "▮"
    }
}