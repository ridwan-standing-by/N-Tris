package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import ktx.math.div
import ktx.math.plus

class LayoutArranger(private val dimensions: Dimensions,
                     private val fonts: Fonts,
                     private val layoutArrangement: LayoutArrangement) {

    private val originBlocks = calculateOriginBlocks()

    private fun calculateOriginBlocks() = dimensions.extraBlocks / 2f

    private fun translate(position: Vector2) = originBlocks + position

    fun createViews(): ArrayList<View> = arrayListOf<View>().apply {
        addAll(layoutArrangement.viewRules.map {
            it.viewConstructor(dimensions, fonts, translate(it.positionBlocks), it.sizeBlocks)
        })
    }
}
