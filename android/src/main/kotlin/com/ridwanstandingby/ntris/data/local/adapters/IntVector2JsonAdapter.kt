package com.ridwanstandingby.ntris.data.local.adapters

import com.ridwanstandingby.ntris.polyomino.geometry.IntVector2
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun IntVector2.toJson(): JSONObject =
        JSONObject().apply {
            put(X, x)
            put(Y, y)
        }

/** @throws JSONException */
fun JSONObject.toIntVector2(): IntVector2 =
        IntVector2(
                getInt(X),
                getInt(Y)
        )

private const val X = "x"
private const val Y = "y"
