package com.ridwanstandingby.ntris.data.adapters

import com.badlogic.gdx.math.Vector2
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Vector2.toJson(): JSONObject =
    JSONObject().apply {
        put(X, x.toDouble())
        put(Y, y.toDouble())
    }

/** @throws JSONException */
fun JSONObject.toVector2(): Vector2 =
    Vector2(
        getDouble(X).toFloat(),
        getDouble(Y).toFloat()
    )

private const val X = "x"
private const val Y = "y"
