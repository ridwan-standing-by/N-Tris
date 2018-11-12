package com.ridwanstandingby.ntris.data.local.adapters

import com.ridwanstandingby.ntris.events.Clock
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Clock.toJson(): JSONObject =
        JSONObject().apply {
            put(T, t.toDouble())
        }

/** @throws JSONException */
fun JSONObject.toClock(): Clock =
        Clock(
                t = getDouble(T).toFloat()
        )

private const val T = "t"
