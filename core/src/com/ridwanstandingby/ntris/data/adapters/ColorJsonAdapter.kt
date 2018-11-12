package com.ridwanstandingby.ntris.data.adapters

import com.badlogic.gdx.graphics.Color
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Color.toJson() =
        JSONObject().apply {
            put(R, r.toDouble())
            put(G, g.toDouble())
            put(B, b.toDouble())
            put(A, a.toDouble())
        }

/** @throws JSONException */
fun JSONObject.toColor(): Color =
        Color(
                getDouble(com.ridwanstandingby.ntris.data.adapters.R).toFloat(),
                getDouble(com.ridwanstandingby.ntris.data.adapters.G).toFloat(),
                getDouble(com.ridwanstandingby.ntris.data.adapters.B).toFloat(),
                getDouble(com.ridwanstandingby.ntris.data.adapters.A).toFloat()
        )

private const val R = "r"
private const val G = "g"
private const val B = "b"
private const val A = "a"
