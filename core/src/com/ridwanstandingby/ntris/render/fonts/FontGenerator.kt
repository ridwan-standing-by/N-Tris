package com.ridwanstandingby.ntris.render.fonts

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.ridwanstandingby.ntris.render.Dimensions

class FontGenerator(private val dimensions: Dimensions) {

    fun generate(): Fonts {
        val generator = FreeTypeFontGenerator(Gdx.files.internal(FontParameters.FONT_PATH))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = dimensions.rescale(FontParameters.FONT_SMALL_SIZE)
        val fontSmall = generator.generateFont(parameter)
        parameter.borderWidth = FontParameters.FONT_BORDER_WIDTH
        parameter.borderColor = FontParameters.FONT_BORDER_COLOUR
        val fontSmallBordered = generator.generateFont(parameter)
        parameter.borderWidth = 0f
        parameter.size = dimensions.rescale(FontParameters.FONT_LARGE_SIZE)
        val fontLarge = generator.generateFont(parameter)
        generator.dispose()
        return Fonts(fontSmall, fontLarge, fontSmallBordered)
    }
}
