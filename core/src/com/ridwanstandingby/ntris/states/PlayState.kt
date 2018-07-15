package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.render.views.LayoutManager

class PlayState(gsm: GameStateManager) : State(gsm) {

    val shapeRenderer = ShapeRenderer()

    private val layoutManager = LayoutManager(gsm.dimensions, gsm.fonts)

    init {
        layoutManager.createViews()
    }

    override fun handleInput() {

    }

    override fun update(dt: Float) {

    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        val block = gsm.dimensions.block

        shapeRenderer.projectionMatrix = cam.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)


        layoutManager.render(sb, shapeRenderer)
//        shapeRenderer.rect(block * 0.5f, block, block * 10f, block * 30f)
//        shapeRenderer.rect(block * 10.5f, block, block * 4, block * 4)
//        shapeRenderer.rect(block * 14.5f, block, block * 4, block * 4)
//        shapeRenderer.rect(block * 10.5f, block * 5, block * 4, block * 4)
//        shapeRenderer.rect(block * 14.5f, block * 5, block * 4, block * 4)
//        shapeRenderer.rect(block * 10.5f, block * 9, block * 4, block * 4)
//        shapeRenderer.rect(block * 14.5f, block * 9, block * 4, block * 4)
//        shapeRenderer.rect(block * 10.5f, block * 13, block * 8, block * 7)
//        shapeRenderer.rect(block * 10.5f, block * 20, block * 8, block * 4)
//        shapeRenderer.rect(block * 10.5f, block * 24, block * 8, block * 7)
        shapeRenderer.end()
        sb.end()
    }

    override fun dispose() {
        shapeRenderer.dispose()
    }
}
