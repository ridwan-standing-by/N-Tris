package com.ridwanstandingby.ntris.render.fonts

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.ridwanstandingby.ntris.render.Dimensions
import ktx.freetype.generateFont

class FontGenerator(private val dimensions: Dimensions) {

    fun generate(): Fonts {
        val boxInfo = generateFont(FontParameters.FONT_BOX_INFO_PATH) {
            size = dimensions.rescale(FontParameters.FONT_BOX_INFO_SIZE).toInt()
        }
        val fontButtonCharacter = generateFont(FontParameters.FONT_BUTTON_CHARACTER_PATH) {
            size = dimensions.rescale(FontParameters.FONT_BUTTON_CHARACTER_SIZE).toInt()
            characters = FreeTypeFontGenerator.DEFAULT_CHARS + "▼◀▶┃↶↷⇄"
        }
        return Fonts(boxInfo, fontButtonCharacter)
    }

    private fun generateFont(path: String, parameters: FreeTypeFontGenerator.FreeTypeFontParameter.() -> Unit): BitmapFont {
        FreeTypeFontGenerator(Gdx.files.internal(path)).let {
            val font = it.generateFont { parameters() }
            it.dispose()
            return font
        }
    }
}
