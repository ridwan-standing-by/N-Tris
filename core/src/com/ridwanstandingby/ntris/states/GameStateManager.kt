package com.ridwanstandingby.ntris.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.game.Game
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.FontGenerator
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import java.util.*

class GameStateManager(private val gameDataManager: GameDataManager) {

    var game = Game(gameDataManager)

    lateinit var dimensions: Dimensions
    lateinit var fonts: Fonts
    lateinit var layoutArrangement: LayoutArrangement

    fun calibrateSize(viewPortWidth: Int, viewPortHeight: Int) {
        dimensions = Dimensions(viewPortWidth, viewPortHeight)
        fonts = FontGenerator(dimensions).generate()
        layoutArrangement = gameDataManager.layoutArrangement
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
        if (game.doRestart) game = Game(gameDataManager)
    }

    fun render(sb: SpriteBatch, sr: ShapeRenderer) {
        states.peek().render(sb, sr)
    }
}
