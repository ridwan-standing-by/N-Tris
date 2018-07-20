package com.ridwanstandingby.ntris.render.views.button

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_DOWN_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_LEFT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.MOVE_RIGHT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.PAUSE_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.ROTATE_LEFT_KEYS
import com.ridwanstandingby.ntris.input.KeyInput.ROTATE_RIGHT_KEYS
import com.ridwanstandingby.ntris.input.RawPlayInput
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontHelper
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.View


sealed class ButtonView(dimensions: Dimensions, fonts: Fonts,
                          originBlocksX: Float, originBlocksY: Float,
                          blocksWidth: Float, blockHeight: Float,
                          private val icon: String, private val offset: Vector2 = Vector2(0f, 0f)) :
        View(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight) {

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderBorder(sr)
        renderTextInCentre(sb, fonts.buttonCharacter, icon)
    }

    private fun renderTextInCentre(sb: SpriteBatch, font: BitmapFont, text: String) {
        val textDims = FontHelper.getDimensionsOfText(font, text)
        val x = originX + width/2 - textDims.x/2 + dimensions.rescale(offset.x)
        val y = originY + height/2 + textDims.y/2 + dimensions.rescale(offset.y)
        font.draw(sb, text, x, y)
    }

    class MoveDownButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, MOVE_DOWN_BUTTON_ICON) {
        override val inputKeys = MOVE_DOWN_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.moveDown = true
        }
    }

    class PauseButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, PAUSE_BUTTON_ICON, PAUSE_BUTTON_ICON_OFFSET) {
        override val inputKeys = PAUSE_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.pause = true
        }
    }

    class MoveLeftButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, MOVE_LEFT_BUTTON_ICON) {
        override val inputKeys = MOVE_LEFT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.moveLeft = true
        }
    }

    class MoveRightButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, MOVE_RIGHT_BUTTON_ICON) {
        override val inputKeys = MOVE_RIGHT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.moveRight = true
        }
    }

    class RotateLeftButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, ROTATE_LEFT_BUTTON_ICON) {
        override val inputKeys = ROTATE_LEFT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.rotateLeft = true
        }
    }

    class RotateRightButtonView(dimensions: Dimensions, fonts: Fonts, originBlocksX: Float, originBlocksY: Float, blocksWidth: Float, blockHeight: Float)
        : ButtonView(dimensions, fonts, originBlocksX, originBlocksY, blocksWidth, blockHeight, ROTATE_RIGHT_BUTTON_ICON) {
        override val inputKeys = ROTATE_RIGHT_KEYS
        override fun handleInputIsInView(rawPlayInput: RawPlayInput) {
            rawPlayInput.rotateRight = true
        }
    }

    companion object {
        const val MOVE_DOWN_BUTTON_ICON = "↓"
        const val MOVE_LEFT_BUTTON_ICON = "←"
        const val MOVE_RIGHT_BUTTON_ICON = "→"
        const val ROTATE_LEFT_BUTTON_ICON = "↶"
        const val ROTATE_RIGHT_BUTTON_ICON = "↷"
        const val PAUSE_BUTTON_ICON = "╻╻"
        private val PAUSE_BUTTON_ICON_OFFSET = Vector2(0f, 32f)
    }
}
