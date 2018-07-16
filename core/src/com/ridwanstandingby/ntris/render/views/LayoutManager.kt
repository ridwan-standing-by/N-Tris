package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.button.ButtonView

class LayoutManager(private val dimensions: Dimensions, private val fonts: Fonts) {

    val views: ArrayList<View> = arrayListOf()

    fun createViews() {
        views.add(object : ButtonView(dimensions, fonts, 10.5f, 1.0f, 4f, 4f, "▼") {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 1.0f, 4f, 4f, "┃┃") {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 10.5f, 5.0f, 4f, 4f, "◀") {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 5.0f, 4f, 4f, "▶") {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 10.5f, 9.0f, 4f, 4f, "↶") {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 9.0f, 4f, 4f, "↷") {
            override fun handleInputInView() {}
        })
    }

    fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        views.forEach {
            it.render(sb, sr)
        }
    }
}