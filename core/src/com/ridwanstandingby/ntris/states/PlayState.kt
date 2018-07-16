package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.views.LayoutManager


class PlayState(gsm: GameStateManager) : State(gsm) {

    private val layoutManager = LayoutManager(gsm.dimensions, gsm.fonts)

    init {
        layoutManager.createViews()
    }

    override fun handleInput() {

    }

    override fun update(dt: Float) {

    }

    override fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        beginRender(sb, sr)
        val block = gsm.dimensions.rescaledBlock()

        layoutManager.render(sb, sr)
        gsm.fonts.boxInfo.draw(sb, "Hello World!", gsm.dimensions.width/2f, gsm.dimensions.height/2f)
        gsm.fonts.boxInfo.draw(sb, "Hello World!", gsm.dimensions.width/3f, gsm.dimensions.height/3f)
        sr.rect(block * 0.5f, block, block * 10f, block * 30f)
        sr.rect(block * 10.5f, block, block * 4, block * 4)
        sr.rect(block * 14.5f, block, block * 4, block * 4)
        sr.rect(block * 10.5f, block * 5, block * 4, block * 4)
        sr.rect(block * 14.5f, block * 5, block * 4, block * 4)
        sr.rect(block * 10.5f, block * 9, block * 4, block * 4)
        sr.rect(block * 14.5f, block * 9, block * 4, block * 4)
        sr.rect(block * 10.5f, block * 13, block * 8, block * 7)
        sr.rect(block * 10.5f, block * 20, block * 8, block * 4)
        sr.rect(block * 10.5f, block * 24, block * 8, block * 7)


        sr.end()
        sb.end()
    }

    private fun beginRender(sb: SpriteBatch, sr: ShapeRenderer) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sr.projectionMatrix = cam.combined
        sr.begin(ShapeRenderer.ShapeType.Line)
    }

    override fun dispose() {}
}
