package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.input.RawPlayInputProcessor
import com.ridwanstandingby.ntris.render.views.LayoutArranger
import com.ridwanstandingby.ntris.render.views.View


class PlayState(gsm: GameStateManager) : State(gsm) {

    private var views: List<View> = LayoutArranger(gsm.dimensions, gsm.fonts).createViews()
    private val rawInputProcessor = RawPlayInputProcessor(gsm)

    init {
        configureInputProcessor()
    }

    override fun handleInput(): Boolean {
        gsm.game.resolvePlayInput(rawInputProcessor.getRawInput(views))
        return true
    }

    override fun update(dt: Float) {
        handleInput()
        gsm.game.update(dt)
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        beginRender(sb, sr)
        views.forEach { it.render(sb, sr, gsm.game) }
        endRender(sb, sr)
    }

    private fun beginRender(sb: SpriteBatch, sr: ShapeRenderer) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sr.projectionMatrix = cam.combined
        sr.begin(ShapeRenderer.ShapeType.Line)
    }

    private fun endRender(sb: SpriteBatch, sr: ShapeRenderer) {
        // Bug workaround - need to render a new font for the previous ones to render
        gsm.fonts.dummy.draw(sb, " ", 0f, 0f)
        sr.end()
        sb.end()
    }

    override fun dispose() {}

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
