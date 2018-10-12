package com.ridwanstandingby.ntris.render.views

import com.ridwanstandingby.ntris.game.GameRules
import com.ridwanstandingby.ntris.render.Dimensions
import com.ridwanstandingby.ntris.render.fonts.Fonts
import com.ridwanstandingby.ntris.render.views.button.ButtonView.*
import com.ridwanstandingby.ntris.render.views.gameOver.GameOverView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.NextView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.ReserveView
import com.ridwanstandingby.ntris.render.views.play.PlayView
import com.ridwanstandingby.ntris.render.views.score.ScoreView

class LayoutArranger(private val dimensions: Dimensions, private val fonts: Fonts) {

    fun createViews(): ArrayList<View> = arrayListOf<View>().apply {
        add(MoveDownButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 01.0f, 4.0f, 4.0f))
        add(ReflectButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 14.5f, dimensions.extraBlocksY/2f + 01.0f, 4.0f, 4.0f))
        add(MoveLeftButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 05.0f, 4.0f, 4.0f))
        add(MoveRightButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 14.5f, dimensions.extraBlocksY/2f + 05.0f, 4.0f, 4.0f))
        add(RotateLeftButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 09.0f, 4.0f, 4.0f))
        add(RotateRightButtonView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 14.5f, dimensions.extraBlocksY/2f + 09.0f, 4.0f, 4.0f))
        add(ReserveView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 13.0f, 8.0f, 7.0f))
        add(ScoreView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 20.0f, 8.0f, 4.0f))
        add(NextView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 10.5f, dimensions.extraBlocksY/2f + 24.0f, 8.0f, 7.0f))
        add(PlayView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 00.5f, dimensions.extraBlocksY/2f + 01.0f,
                GameRules.PLAY_BLOCK_SIZE.x.toFloat(),
                GameRules.PLAY_BLOCK_SIZE.y.toFloat()))
        add(GameOverView(dimensions, fonts,
                dimensions.extraBlocksX/2f + 01.5f, dimensions.extraBlocksY/2f + 13.0f, 8.0f, 7.0f))
    }
}
