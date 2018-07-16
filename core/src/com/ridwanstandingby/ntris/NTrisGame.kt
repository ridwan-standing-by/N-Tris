package com.ridwanstandingby.ntris

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontGenerator
import com.ridwanstandingby.ntris.states.GameStateManager
import com.ridwanstandingby.ntris.states.PlayState

class NTrisGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var shapeRenderer: ShapeRenderer
    private lateinit var gsm: GameStateManager

    override fun create() {
        batch = SpriteBatch()
        shapeRenderer = ShapeRenderer()
        initGameStateManager()
        initPlayState()
    }

    private fun initGameStateManager() {
        val dimensions = Dimensions(Gdx.graphics.width, Gdx.graphics.height)
        val fonts = FontGenerator(dimensions).generate()
        gsm = GameStateManager(dimensions, fonts)
    }

    private fun initPlayState() {
        gsm.push(PlayState(gsm))
    }

    override fun render() {
        clearScreen()
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(batch, shapeRenderer)
    }

    private fun clearScreen() = Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

    override fun dispose() {
        batch.dispose()
        shapeRenderer.dispose()
    }
}
