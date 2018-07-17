package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.button.ButtonView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.NextView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.ReserveView
import com.ridwanstandingby.ntris.render.views.play.PlayView
import com.ridwanstandingby.ntris.render.views.score.ScoreView

class LayoutManager(private val dimensions: Dimensions, private val fonts: Fonts) {

    val views: ArrayList<View> = arrayListOf()

    fun createViews() {
        views.add(PlayView(dimensions, fonts, 0.5f, 1.0f, 10f, 30f))

        views.add(object : ButtonView(dimensions, fonts, 10.5f, 1.0f, 4f, 4f, MOVE_DOWN_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 1.0f, 4f, 4f, PAUSE_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 10.5f, 5.0f, 4f, 4f, MOVE_LEFT_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 5.0f, 4f, 4f, MOVE_RIGHT_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 10.5f, 9.0f, 4f, 4f, ROTATE_LEFT_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(object : ButtonView(dimensions, fonts, 14.5f, 9.0f, 4f, 4f, ROTATE_RIGHT_BUTTON_ICON) {
            override fun handleInputInView() {}
        })

        views.add(ReserveView(dimensions, fonts, 10.5f, 13.0f, 8.0f, 7.0f))
        views.add(ScoreView(dimensions, fonts, 10.5f, 20.0f, 8.0f, 4.0f))
        views.add(NextView(dimensions, fonts, 10.5f, 24.0f, 8.0f, 7.0f))
    }

    fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        views.forEach {
            it.render(sb, sr)
        }
    }
}