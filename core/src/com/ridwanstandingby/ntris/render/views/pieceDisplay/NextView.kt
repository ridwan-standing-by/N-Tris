package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.NEXT_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

class NextView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2) :
    PieceDisplayView(dimensions, fonts, originBlocks, sizeBlocks) {

    override val boxInfoText = NEXT_BOX_INFO_TEXT
    override val boxIcon = NEXT_BOX_INFO_ICON
    override val scaledPadding = dimensions.rescale(TEXT_PADDING)
    override val inputKeys = NEXT_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.next = true
    }

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        renderBorder(sr)
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        renderText(sb)
        renderIcon(sb)
        renderPiece(sb, game.nextPiece)
    }

    companion object {
        const val NEXT_BOX_INFO_TEXT = "Next"
        const val NEXT_BOX_INFO_ICON = "⧐"
        const val TEXT_PADDING = 16
    }
}
