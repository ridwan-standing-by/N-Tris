package com.ridwanstandingby.ntris.render.views.play

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.PLAY_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.polyomino.BlockRenderer
import com.ridwanstandingby.ntris.render.views.View

class PlayView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2) :
    View(dimensions, fonts, originBlocks, sizeBlocks) {

    private val scaledInputPadding = INPUT_PADDING_BLOCKS * dimensions.rescaledBlock()

    private val polyominoRenderer = BlockRenderer(dimensions, origin)

    override val inputKeys = PLAY_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.play = true
    }

    override fun wasPointerInView(x: Float, y: Float): Boolean {
        return origin.x + scaledInputPadding <= x && x < (origin.x + size.x) - scaledInputPadding
                && origin.y + scaledInputPadding <= y && y < origin.y + size.y - scaledInputPadding
    }

    override fun renderBlocks(sb: SpriteBatch, game: Game) {
        renderBackgroundBlockMap(sb, game)
        renderCurrentPiece(sb, game)
    }

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        renderBorder(sr)
    }

    private fun renderBackgroundBlockMap(sb: SpriteBatch, game: Game) {
        polyominoRenderer.renderBlocks(sb, game.backgroundBlockMap.blocks, IntVector2(sizeBlocks))
    }

    private fun renderCurrentPiece(sb: SpriteBatch, game: Game) {
        polyominoRenderer.renderPolyomino(sb, game.currentPiece, IntVector2(sizeBlocks))
    }

    companion object {
        private const val INPUT_PADDING_BLOCKS = 1.5f
    }
}
