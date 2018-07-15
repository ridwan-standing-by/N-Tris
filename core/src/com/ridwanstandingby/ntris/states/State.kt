package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State(var gsm: GameStateManager) {

    var cam: OrthographicCamera = OrthographicCamera()

    abstract fun handleInput()
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch)
    abstract fun dispose()
}
