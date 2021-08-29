package com.ridwanstandingby.ntris.render.views.pieceDisplay

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.polyomino.Polyomino
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.polyomino.BlockRenderer
import com.ridwanstandingby.ntris.render.views.View

abstract class PieceDisplayView(
    dimensions: Dimensions,
    fonts: Fonts,
    originBlocks: Vector2,
    sizeBlocks: Vector2
) : View(dimensions, fonts, originBlocks, sizeBlocks) {

    private val polyominoRenderer = BlockRenderer(dimensions, origin)

    protected abstract val boxInfoText: String
    protected abstract val boxIcon: String
    protected abstract val scaledPadding: Float

    protected fun renderPiece(sb: SpriteBatch, polyomino: Polyomino) {
        val offset = IntVector2(sizeBlocks) / 2
        polyominoRenderer.renderPolyomino(sb, polyomino, IntVector2(sizeBlocks), offset)
    }

    protected fun renderText(sb: SpriteBatch) {
        val x = origin.x + scaledPadding
        val y = origin.y + size.y - scaledPadding
        fonts.boxInfo.draw(sb, boxInfoText, x, y)
    }

    protected fun renderIcon(sb: SpriteBatch) {
        val textDims = FontHelper.getDimensionsOfText(fonts.boxCharacter, boxIcon)
        val x = origin.x + size.x - scaledPadding - textDims.x
        val y = origin.y + size.y - scaledPadding
        fonts.boxCharacter.draw(sb, boxIcon, x, y)
    }
}
