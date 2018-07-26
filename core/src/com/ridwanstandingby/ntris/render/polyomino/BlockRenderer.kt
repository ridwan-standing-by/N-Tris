package com.ridwanstandingby.ntris.render.polyomino

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ridwanstandingby.ntris.polyomino.Block
import com.ridwanstandingby.ntris.polyomino.Polyomino
import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import com.ridwanstandingby.ntris.render.Dimensions

class BlockRenderer(private val dimensions: Dimensions, private val originX: Float, private val originY: Float) {

    private val blockTexture = Texture(BLOCK_TEXTURE)

    fun renderPolyomino(sb: SpriteBatch, polyomino: Polyomino, bounds: IntVector2, blockOffset: IntVector2 = IntVector2(0, 0)) {
        renderBlocks(sb, polyomino.generateBlocks(), bounds, blockOffset)
    }

    fun renderBlocks(sb: SpriteBatch, blocks: Iterable<Block>, bounds: IntVector2, blockOffset: IntVector2 = IntVector2(0, 0)) {
        val sprite = Sprite(blockTexture)
        sprite.setSize(dimensions.rescaledBlock(), dimensions.rescaledBlock())
        blocks.filter { block -> positionIsInBounds(block.position + blockOffset, bounds) }
                .forEach { block ->
                    sprite.color = block.colour
                    val screenPosition = (block.position + blockOffset) * dimensions.rescaledBlock()
                    sprite.setPosition(originX + screenPosition.x, originY + screenPosition.y)
                    sprite.draw(sb)
                }
    }

    private fun positionIsInBounds(offsetBlockPosition: IntVector2, bounds: IntVector2) =
            0 <= offsetBlockPosition.x && offsetBlockPosition.x < bounds.x &&
                    0 <= offsetBlockPosition.y && offsetBlockPosition.y < bounds.y

    companion object {
        const val BLOCK_TEXTURE = "polyominos/block.png"
    }
}
