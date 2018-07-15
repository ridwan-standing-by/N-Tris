package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State(protected var gsm: GameStateManager) {

    protected val cam: OrthographicCamera = OrthographicCamera()

    init {
        cam.setToOrtho(false, gsm.dimensions.width.toFloat(), gsm.dimensions.height.toFloat())
    }

    abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch)
    abstract fun dispose()
}
