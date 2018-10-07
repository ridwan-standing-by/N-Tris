package com.ridwanstandingby.ntris.render.views.play

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.PLAY_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.polyomino.BlockRenderer
import com.ridwanstandingby.ntris.render.views.View


class PlayView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

    private val polyominoRenderer = BlockRenderer(dimensions, originX, originY)

    override val inputKeys = PLAY_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.play = true
    }

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        renderBorder(sr)
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        renderBackgroundBlockMap(sb, game)
        renderCurrentPiece(sb, game)
    }

    private fun renderBackgroundBlockMap(sb: SpriteBatch, game: Game) {
        polyominoRenderer.renderBlocks(sb, game.backgroundBlockMap.blocks, IntVector2(blocksWidth, blocksHeight))
    }

    private fun renderCurrentPiece(sb: SpriteBatch, game: Game) {
        polyominoRenderer.renderPolyomino(sb, game.currentPiece, IntVector2(blocksWidth, blocksHeight))
    }
}
