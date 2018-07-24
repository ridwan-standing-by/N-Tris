package com.ridwanstandingby.ntris.render.views.play

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.Game
import com.ridwanstandingby.ntris.input.KeyInput.PLAY_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.polyomino.PolyominoRenderer
import com.ridwanstandingby.ntris.render.views.View


class PlayView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blocksHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blocksHeight) {

    private val polyominoRenderer = PolyominoRenderer(dimensions, originX, originY)

    override val inputKeys = PLAY_KEYS

    override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.play = true
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer, game: Game) {
        renderBorder(sr)
        renderCurrentPiece(sb, game)
    }

    private fun renderCurrentPiece(sb: SpriteBatch, game: Game) {
        polyominoRenderer.render(sb, game.currentPiece)
    }
}
