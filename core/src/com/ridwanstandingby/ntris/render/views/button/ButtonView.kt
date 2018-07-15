package com.ridwanstandingby.ntris.render.views.button

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

abstract class ButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float, private val icon: String) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override fun render(sb: SpriteBatch, shapeRenderer: ShapeRenderer) {
        renderBorder(shapeRenderer)
        renderTextInCentre(sb, fonts.fontSmall, icon)
    }

    private fun renderTextInCentre(sb: SpriteBatch, font: BitmapFont, text: String) {
        val textDims = getDimensionsOfText(font, text)
        font.draw(sb, text, originX + width/2 - textDims.x/2, originY + height/2 - textDims.y/2)
    }

    private fun getDimensionsOfText(font: BitmapFont, text: String): Vector2 {
        val glyphLayout = GlyphLayout()
        glyphLayout.setText(font, text)
        return Vector2(glyphLayout.width, glyphLayout.height)
    }
}