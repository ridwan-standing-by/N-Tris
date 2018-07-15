package com.ridwanstandingby.ntris

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.states.GameStateManager

class NTrisGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var gsm: GameStateManager

    override fun create() {
        val dimensions = computeDimensions()
        batch = SpriteBatch()
        gsm = GameStateManager(dimensions)
    }

    private fun computeDimensions() = Dimensions(Gdx.graphics.width, Gdx.graphics.height)

    override fun render() {
        clearScreen()
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(batch)
    }

    private fun clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun dispose() {
        batch.dispose()
    }
}
