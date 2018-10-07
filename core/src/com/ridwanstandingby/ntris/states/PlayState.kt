package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.input.RawPlayInputProcessor
import com.ridwanstandingby.ntris.render.views.LayoutArranger
import com.ridwanstandingby.ntris.render.views.View


class PlayState(gsm: GameStateManager) : State(gsm) {

    private var views: List<View> = LayoutArranger(gsm.dimensions, gsm.fonts).createViews()
    private val rawInputProcessor = RawPlayInputProcessor(gsm)

    override fun handleInput(): Boolean {
        gsm.game.resolvePlayInput(rawInputProcessor.getRawInput(views))
        return true
    }

    override fun update(dt: Float) {
        handleInput()
        gsm.game.update(dt)
    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        renderShapes(sr)
        renderSprites(sb)
    }

    private fun renderShapes(sr: ShapeRenderer) {
        sr.projectionMatrix = cam.combined
        sr.setAutoShapeType(true)
        sr.begin()
        views.forEach { it.renderShapes(sr, gsm.game) }
        sr.end()
    }

    private fun renderSprites(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        views.forEach { it.renderSprites(sb, gsm.game) }
        sb.end()
    }

    override fun dispose() {}
}
