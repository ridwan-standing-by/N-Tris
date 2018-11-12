package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.polyomino.Block
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Block.toJson(): JSONObject =
        JSONObject().apply {
            put(POSITION, position.toJson())
            put(COLOUR, colour.toJson())
        }

/** @throws JSONException */
fun JSONObject.toBlock(): Block =
        Block(
                position = getJSONObject(com.ridwanstandingby.ntris.data.adapters.POSITION).toIntVector2(),
                colour = getJSONObject(com.ridwanstandingby.ntris.data.adapters.COLOUR).toColor()
        )

private const val POSITION = "position"
private const val COLOUR = "colour"
