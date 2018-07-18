package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

class ReserveView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float) :
        PieceDisplayView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override val boxInfoText = RESERVE_BOX_INFO_TEXT
    override val boxIcon = RESERVE_BOX_INFO_ICON
    override val scaledPadding = dimensions.rescale(TEXT_PADDING)

    override fun handleInputInView() {
        // TODO
    }

    override fun renderPiece() {
        // TODO
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderBorder(sr)
        renderText(sb)
        renderIcon(sb)
        renderPiece()
    }

    companion object {
        const val RESERVE_BOX_INFO_TEXT = "Reserve"
        const val RESERVE_BOX_INFO_ICON = "⧎"
        const val TEXT_PADDING = 16
    }
}