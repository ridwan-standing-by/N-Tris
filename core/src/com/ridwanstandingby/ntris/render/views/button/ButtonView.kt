package com.ridwanstandingby.ntris.render.views.button

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_DOWN_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_LEFT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_RIGHT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.REFLECT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.ROTATE_LEFT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.ROTATE_RIGHT_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View


sealed class ButtonView(dimensions: Dimensions, fonts: Fonts,
                        originBlocks: Vector2, sizeBlocks: Vector2,
                        private val icon: String) :
        View(dimensions, fonts, originBlocks, sizeBlocks) {

    override fun renderShapes(sr: ShapeRenderer, game: Game) {
        renderHighlightIfQueued(sr)
        renderBorder(sr)
    }

    override fun renderSprites(sb: SpriteBatch, game: Game) {
        renderTextInCentre(sb, fonts.buttonCharacter, icon)
    }

    private fun renderTextInCentre(sb: SpriteBatch, font: BitmapFont, text: String) {
        val textDims = FontHelper.getDimensionsOfText(font, text)
        val x = origin.x + size.x / 2 - textDims.x / 2
        val y = origin.y + size.y / 2 + textDims.y / 2
        font.draw(sb, text, x, y)
    }

    class MoveDownButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, MOVE_DOWN_BUTTON_ICON) {
        override val inputKeys = MOVE_DOWN_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.moveDown = true
        }
    }

    class ReflectButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, REFLECT_BUTTON_ICON) {
        override val inputKeys = REFLECT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.reflect = true
        }
    }

    class MoveLeftButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, MOVE_LEFT_BUTTON_ICON) {
        override val inputKeys = MOVE_LEFT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.moveLeft = true
        }
    }

    class MoveRightButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, MOVE_RIGHT_BUTTON_ICON) {
        override val inputKeys = MOVE_RIGHT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.moveRight = true
        }
    }

    class RotateLeftButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, ROTATE_LEFT_BUTTON_ICON) {
        override val inputKeys = ROTATE_LEFT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.rotateLeft = true
        }
    }

    class RotateRightButtonView(dimensions: Dimensions, fonts: Fonts, originBlocks: Vector2, sizeBlocks: Vector2)
        : ButtonView(dimensions, fonts, originBlocks, sizeBlocks, ROTATE_RIGHT_BUTTON_ICON) {
        override val inputKeys = ROTATE_RIGHT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            queueHighlight = true
            rawPlayInput.rotateRight = true
        }
    }

    companion object {
        const val MOVE_DOWN_BUTTON_ICON = "↓"
        const val MOVE_LEFT_BUTTON_ICON = "←"
        const val MOVE_RIGHT_BUTTON_ICON = "→"
        const val ROTATE_LEFT_BUTTON_ICON = "↶"
        const val ROTATE_RIGHT_BUTTON_ICON = "↷"
        const val REFLECT_BUTTON_ICON = "⇹"
    }
}
