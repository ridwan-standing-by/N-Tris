package com.ridwanstandingby.ntris.render.polyomino

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ridwanstandingby.ntris.polyomino.Polyomino
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions

class PolyominoRenderer(private val dimensions: Dimensions, private val originX: Float, private val originY: Float) {

    private val blockTexture = Texture(BLOCK_TEXTURE)

    fun render(sb: SpriteBatch, polyomino: Polyomino, blockOffset: IntVector2 = IntVector2(0, 0)) {
        val sprite = Sprite(blockTexture)
        sprite.setPosition(
                originX + dimensions.rescaledBlock() * (polyomino.position.x + blockOffset.x),
                originY + dimensions.rescaledBlock() * (polyomino.position.y + blockOffset.y))
        sprite.setSize(dimensions.rescaledBlock(), dimensions.rescaledBlock())
        sprite.color = polyomino.colour
        sprite.draw(sb)
    }

    companion object {
        const val BLOCK_TEXTURE = "polyominos/block.png"
    }
}
