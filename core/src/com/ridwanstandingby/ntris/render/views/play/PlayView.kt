package com.ridwanstandingby.ntris.render.views.play

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View

class PlayView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override fun handleInputInView(rawPlayInput: RawPlayInput) {
        rawPlayInput.play = true
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderBorder(sr)
    }
}
