package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.RESERVE_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

class ReserveView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        PieceDisplayView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

    override val boxInfoText = RESERVE_BOX_INFO_TEXT
    override val boxIcon = RESERVE_BOX_INFO_ICON
    override val scaledPadding = dimensions.rescale(TEXT_PADDING)
    override val inputKeys = RESERVE_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        queueHighlight = true
        rawPlayInput.reserve = true
    }

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        renderHighlightIfQueued(sr)
        renderBorder(sr)
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        renderText(sb)
        renderIcon(sb)
        renderPiece(sb, game.reservePiece)
    }

    companion object {
        const val RESERVE_BOX_INFO_TEXT = "Reserve"
        const val RESERVE_BOX_INFO_ICON = "⧎"
        const val TEXT_PADDING = 16
    }
}
