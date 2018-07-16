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
        sb.projectionMatrix = cam.combined
        sb.begin()
        val block = gsm.dimensions.rescaledBlock()

        sr.projectionMatrix = cam.combined
        sr.begin(ShapeRenderer.ShapeType.Line)

        layoutManager.render(sb, sr)
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
        gsm.fonts.fontLarge.draw(sb, "Hello World!", gsm.dimensions.width/2f, gsm.dimensions.height/2f)
        gsm.fonts.fontSmall.draw(sb, "Hello World!", gsm.dimensions.width/3f, gsm.dimensions.height/3f)

        sr.end()
        sb.end()
    }

    override fun dispose() {}
}
