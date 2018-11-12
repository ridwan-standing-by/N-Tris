package com.ridwanstandingby.ntris.data.local.adapters

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
                getDouble(R).toFloat(),
                getDouble(G).toFloat(),
                getDouble(B).toFloat(),
                getDouble(A).toFloat()
        )

private const val R = "r"
private const val G = "g"
private const val B = "b"
private const val A = "a"
