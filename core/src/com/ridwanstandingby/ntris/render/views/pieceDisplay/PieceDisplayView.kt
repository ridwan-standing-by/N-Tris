package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ridwanstandingby.ntris.polyomino.Polyomino
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.polyomino.BlockRenderer
import com.ridwanstandingby.ntris.render.views.View

abstract class PieceDisplayView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

    private val polyominoRenderer = BlockRenderer(dimensions, originX, originY)

    protected abstract val boxInfoText: String
    protected abstract val boxIcon: String
    protected abstract val scaledPadding: Float

    protected fun renderPiece(sb: SpriteBatch, polyomino: Polyomino) {
        val offset = IntVector2(blocksWidth, blocksHeight) / 2
        polyominoRenderer.renderPolyomino(sb, polyomino, offset)
    }

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
