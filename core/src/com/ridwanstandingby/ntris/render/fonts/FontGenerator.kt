package com.ridwanstandingby.ntris.render.fonts

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.views.button.ButtonView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.NextView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.ReserveView
import com.ridwanstandingby.ntris.render.views.score.ScoreView
import ktx.freetype.generateFont


class FontGenerator(private val dimensions: Dimensions) {

    fun generate(): Fonts {
        val boxInfo = generateFont(FontParameters.FONT_BOX_INFO_PATH) {
            size = dimensions.rescale(FontParameters.FONT_BOX_INFO_SIZE).toInt()
        }
        val boxCharacter = generateFont(FontParameters.FONT_BUTTON_CHARACTER_PATH) {
            size = dimensions.rescale(FontParameters.FONT_BOX_INFO_SIZE).toInt()
            characters = FreeTypeFontGenerator.DEFAULT_CHARS + BOX_CHARACTER_SET
        }
        val buttonCharacter = generateFont(FontParameters.FONT_BUTTON_CHARACTER_PATH) {
            size = dimensions.rescale(FontParameters.FONT_BUTTON_CHARACTER_SIZE).toInt()
            characters = FreeTypeFontGenerator.DEFAULT_CHARS + BUTTON_CHARACTER_SET
        }
        val dummy = generateFont(FontParameters.FONT_BOX_INFO_PATH) {
            size = 10
        }
        return Fonts(boxInfo, boxCharacter, buttonCharacter, dummy)
    }

    private fun generateFont(path: String, parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit): BitmapFont {
        FreeTypeFontGenerator(Gdx.files.internal(path)).let {
            val font = it.generateFont { parameters() }
            it.dispose()
            return font
        }
    }

    companion object {
        private const val BOX_CHARACTER_SET = NextView.NEXT_BOX_INFO_ICON +
                        ReserveView.RESERVE_BOX_INFO_ICON +
                        ScoreView.CURRENT_SCORE_ICON +
                        ScoreView.HIGH_SCORE_ICON

        private const val BUTTON_CHARACTER_SET = ButtonView.MOVE_DOWN_BUTTON_ICON +
                        ButtonView.MOVE_LEFT_BUTTON_ICON +
                        ButtonView.MOVE_RIGHT_BUTTON_ICON +
                        ButtonView.ROTATE_LEFT_BUTTON_ICON +
                        ButtonView.ROTATE_RIGHT_BUTTON_ICON +
                        ButtonView.REFLECT_BUTTON_ICON
    }

}
