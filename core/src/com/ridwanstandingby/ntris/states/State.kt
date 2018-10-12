package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

abstract class State(protected var gsm: GameStateManager) {

    protected val cam: OrthographicCamera = OrthographicCamera()

    init {
        cam.setToOrtho(false, gsm.dimensions.screenWidth.toFloat(), gsm.dimensions.screenHeight.toFloat())
        configureInputProcessor()
        Gdx.input.isCatchBackKey = true
    }

    abstract fun handleInput(): Boolean
    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch, sr: ShapeRenderer)
    abstract fun dispose()

    private fun configureInputProcessor() {
        Gdx.input.inputProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int) = handleInput()
            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = handleInput()
            override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = handleInput()
            override fun touchDragged(screenX: Int, screenY: Int, pointer: Int) = handleInput()
            override fun keyUp(keycode: Int) = handleInput()
        }
    }
}
