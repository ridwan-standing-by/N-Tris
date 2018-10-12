package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.game.GameRules
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.button.ButtonView.*
import com.ridwanstandingby.ntris.render.views.gameOver.GameOverView
import com.ridwanstandingby.ntris.render.views.pause.PauseRestartView
import com.ridwanstandingby.ntris.render.views.pause.PauseResumeView
import com.ridwanstandingby.ntris.render.views.pause.PauseView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.NextView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.ReserveView
import com.ridwanstandingby.ntris.render.views.play.PlayView
import com.ridwanstandingby.ntris.render.views.score.ScoreView
import ktx.math.div
import ktx.math.plus

class LayoutArranger(private val dimensions: Dimensions, private val fonts: Fonts) {

    private val originBlocks = calculateOriginBlocks()

    private fun calculateOriginBlocks() = dimensions.extraBlocks / 2f

    private fun translate(position: Vector2) = originBlocks + position

    fun createViews(): ArrayList<View> = arrayListOf<View>().apply {
        add(MoveDownButtonView(dimensions, fonts,
                translate(Vector2(10.5f, 01.0f)), Vector2(4.0f, 4.0f)))
        add(ReflectButtonView(dimensions, fonts,
                translate(Vector2(14.5f, 01.0f)), Vector2(4.0f, 4.0f)))
        add(MoveLeftButtonView(dimensions, fonts,
                translate(Vector2(10.5f, 05.0f)), Vector2(4.0f, 4.0f)))
        add(MoveRightButtonView(dimensions, fonts,
                translate(Vector2(14.5f, 05.0f)), Vector2(4.0f, 4.0f)))
        add(RotateLeftButtonView(dimensions, fonts,
                translate(Vector2(10.5f, 09.0f)), Vector2(4.0f, 4.0f)))
        add(RotateRightButtonView(dimensions, fonts,
                translate(Vector2(14.5f, 09.0f)), Vector2(4.0f, 4.0f)))
        add(ReserveView(dimensions, fonts,
                translate(Vector2(10.5f, 13.0f)), Vector2(8.0f, 7.0f)))
        add(ScoreView(dimensions, fonts,
                translate(Vector2(10.5f, 20.0f)), Vector2(8.0f, 4.0f)))
        add(NextView(dimensions, fonts,
                translate(Vector2(10.5f, 24.0f)), Vector2(8.0f, 7.0f)))
        add(PlayView(dimensions, fonts,
                translate(Vector2(00.5f, 01.0f)), GameRules.PLAY_BLOCK_SIZE.toFloat()))
        add(GameOverView(dimensions, fonts,
                translate(Vector2(01.5f, 13.0f)), Vector2(8.0f, 7.0f)))
        add(PauseView(dimensions, fonts,
                translate(Vector2(00.5f, 01.0f)), Vector2(10.0f, 30.0f)))
        add(PauseResumeView(dimensions, fonts,
                translate(Vector2(01.5f, 02.0f)), Vector2(8.0f, 7.0f)))
        add(PauseRestartView(dimensions, fonts,
                translate(Vector2(01.5f, 10.0f)), Vector2(8.0f, 7.0f)))
    }
}
