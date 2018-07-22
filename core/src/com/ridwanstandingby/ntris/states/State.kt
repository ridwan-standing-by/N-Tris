package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

abstract class State(protected var gsm: GameStateManager) {

    protected val cam: OrthographicCamera = OrthographicCamera()

    init {
        cam.setToOrtho(false, gsm.dimensions.screenWidth.toFloat(), gsm.dimensions.screenHeight.toFloat())
    }

    abstract fun handleInput(): Boolean
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch, sr: ShapeRenderer)
    abstract fun dispose()
}
