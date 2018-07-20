package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

class NextView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float) :
        PieceDisplayView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override val boxInfoText = NEXT_BOX_INFO_TEXT
    override val boxIcon = NEXT_BOX_INFO_ICON
    override val scaledPadding = dimensions.rescale(TEXT_PADDING)

    override fun handleInputInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.next = true
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
        const val NEXT_BOX_INFO_TEXT = "Next"
        const val NEXT_BOX_INFO_ICON = "⧐"
        const val TEXT_PADDING = 16
    }
}
