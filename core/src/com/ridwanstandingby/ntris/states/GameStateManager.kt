package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.Game
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontGenerator
import com.ridwanstandingby.ntris.render.fonts.Fonts
import java.util.*

class GameStateManager {

    val gameState = Game()

    lateinit var dimensions: Dimensions
    lateinit var fonts: Fonts

    fun calibrateSize(viewPortWidth: Int, viewPortHeight: Int) {
        dimensions = Dimensions(viewPortWidth, viewPortHeight)
        fonts = FontGenerator(dimensions).generate()
    }

    private val states: Stack<State> = Stack()

    fun push(state: State) {
        states.push(state)
    }

    fun pop() {
        states.pop()
    }

    fun set(state: State) {
        states.pop()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        states.peek().render(sb, sr)
    }
}
