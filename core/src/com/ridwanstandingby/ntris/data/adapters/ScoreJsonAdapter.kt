package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.game.Score
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Score.toJson(): JSONObject =
    JSONObject().apply {
        put(POINTS, points)
        put(LINES, lines)
    }

/** @throws JSONException */
fun JSONObject.toScore(): Score =
    Score(
        points = getInt(POINTS),
        lines = getInt(LINES)
    )

private const val POINTS = "points"
private const val LINES = "lines"
