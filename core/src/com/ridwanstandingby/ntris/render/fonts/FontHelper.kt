package com.ridwanstandingby.ntris.render.fonts

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.math.Vector2


object FontHelper {

    fun getDimensionsOfText(font: BitmapFont, text: String): Vector2 {
        val glyphLayout = GlyphLayout()
        glyphLayout.setText(font, text)
        return Vector2(glyphLayout.width, glyphLayout.height)
    }
}
