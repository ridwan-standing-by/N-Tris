package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

abstract class PieceDisplayView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    protected abstract val boxInfoText: String
    protected abstract val boxIcon: String
    protected abstract val scaledPadding: Float

    protected abstract fun renderPiece()

    protected fun renderText(sb: SpriteBatch) {
        val x = originX + scaledPadding
        val y = originY + height - scaledPadding
        fonts.boxInfo.draw(sb, boxInfoText, x, y)
    }

    protected fun renderIcon(sb: SpriteBatch) {
        val textDims = FontHelper.getDimensionsOfText(fonts.boxCharacter, boxIcon)
        val x = originX + width - scaledPadding - textDims.x
        val y = originY + height - scaledPadding
        fonts.boxCharacter.draw(sb, boxIcon, x, y)
    }
}