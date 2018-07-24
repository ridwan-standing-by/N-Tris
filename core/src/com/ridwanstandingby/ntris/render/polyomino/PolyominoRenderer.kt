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
        sprite.setSize(dimensions.rescaledBlock(), dimensions.rescaledBlock())
        sprite.color = polyomino.colour
        polyomino.blocks.forEach { p ->
            val screenPosition = (polyomino.position - polyomino.positionalCentre + blockOffset + p) * dimensions.rescaledBlock()
            sprite.setPosition(originX + screenPosition.x, originY + screenPosition.y)
            sprite.draw(sb)
        }
    }

    companion object {
        const val BLOCK_TEXTURE = "polyominos/block.png"
    }
}
