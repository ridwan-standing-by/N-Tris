package com.ridwanstandingby.ntris.render.views

import com.badlogic.gdx.math.Vector2
import com.ridwanstandingby.ntris.render.views.button.ButtonView
import com.ridwanstandingby.ntris.render.views.gameOver.GameOverView
import com.ridwanstandingby.ntris.render.views.pause.PauseRestartView
import com.ridwanstandingby.ntris.render.views.pause.PauseResumeView
import com.ridwanstandingby.ntris.render.views.pause.PauseView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.NextView
import com.ridwanstandingby.ntris.render.views.pieceDisplay.ReserveView
import com.ridwanstandingby.ntris.render.views.play.PlayView
import com.ridwanstandingby.ntris.render.views.score.ScoreView

enum class LayoutArrangement(val code: String, val viewRules: List<ViewRule>) {

    RIGHT_HANDED(
        "R", listOf(
            ViewRule(Vector2(10.5f, 01.0f), Vector2(04.0f, 04.0f), ButtonView::MoveDownButtonView),
            ViewRule(Vector2(14.5f, 01.0f), Vector2(04.0f, 04.0f), ButtonView::ReflectButtonView),
            ViewRule(Vector2(10.5f, 05.0f), Vector2(04.0f, 04.0f), ButtonView::MoveLeftButtonView),
            ViewRule(Vector2(14.5f, 05.0f), Vector2(04.0f, 04.0f), ButtonView::MoveRightButtonView),
            ViewRule(
                Vector2(10.5f, 09.0f),
                Vector2(04.0f, 04.0f),
                ButtonView::RotateLeftButtonView
            ),
            ViewRule(
                Vector2(14.5f, 09.0f),
                Vector2(04.0f, 04.0f),
                ButtonView::RotateRightButtonView
            ),
            ViewRule(Vector2(10.5f, 13.0f), Vector2(08.0f, 07.0f), ::ReserveView),
            ViewRule(Vector2(10.5f, 20.0f), Vector2(08.0f, 04.0f), ::ScoreView),
            ViewRule(Vector2(10.5f, 24.0f), Vector2(08.0f, 07.0f), ::NextView),
            ViewRule(Vector2(00.5f, 01.0f), Vector2(10.0f, 30.0f), ::PlayView),
            ViewRule(Vector2(01.5f, 13.0f), Vector2(08.0f, 07.0f), ::GameOverView),
            ViewRule(Vector2(00.5f, 01.0f), Vector2(10.0f, 30.0f), ::PauseView),
            ViewRule(Vector2(01.5f, 02.0f), Vector2(08.0f, 07.0f), ::PauseResumeView),
            ViewRule(Vector2(01.5f, 10.0f), Vector2(08.0f, 07.0f), ::PauseRestartView)
        )
    ),

    LEFT_HANDED(
        "L", listOf(
            ViewRule(Vector2(04.5f, 01.0f), Vector2(04.0f, 04.0f), ButtonView::MoveDownButtonView),
            ViewRule(Vector2(00.5f, 01.0f), Vector2(04.0f, 04.0f), ButtonView::ReflectButtonView),
            ViewRule(Vector2(00.5f, 05.0f), Vector2(04.0f, 04.0f), ButtonView::MoveLeftButtonView),
            ViewRule(Vector2(04.5f, 05.0f), Vector2(04.0f, 04.0f), ButtonView::MoveRightButtonView),
            ViewRule(
                Vector2(00.5f, 09.0f),
                Vector2(04.0f, 04.0f),
                ButtonView::RotateLeftButtonView
            ),
            ViewRule(
                Vector2(04.5f, 09.0f),
                Vector2(04.0f, 04.0f),
                ButtonView::RotateRightButtonView
            ),
            ViewRule(Vector2(00.5f, 13.0f), Vector2(08.0f, 07.0f), ::ReserveView),
            ViewRule(Vector2(00.5f, 20.0f), Vector2(08.0f, 04.0f), ::ScoreView),
            ViewRule(Vector2(00.5f, 24.0f), Vector2(08.0f, 07.0f), ::NextView),
            ViewRule(Vector2(08.5f, 01.0f), Vector2(10.0f, 30.0f), ::PlayView),
            ViewRule(Vector2(09.5f, 13.0f), Vector2(08.0f, 07.0f), ::GameOverView),
            ViewRule(Vector2(08.5f, 01.0f), Vector2(10.0f, 30.0f), ::PauseView),
            ViewRule(Vector2(09.5f, 02.0f), Vector2(08.0f, 07.0f), ::PauseResumeView),
            ViewRule(Vector2(09.5f, 10.0f), Vector2(08.0f, 07.0f), ::PauseRestartView)
        )
    );

    companion object {

        fun fromCode(code: String): LayoutArrangement = values().firstOrNull { it.code == code }
            ?: DEFAULT

        val DEFAULT = RIGHT_HANDED
    }
}
