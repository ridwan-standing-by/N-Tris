package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts

class ViewRule(
    val positionBlocks: Vector2,
    val sizeBlocks: Vector2,
    val viewConstructor: (Dimensions, Fonts, Vector2, Vector2) -> View
)
