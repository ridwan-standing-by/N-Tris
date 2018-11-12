package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.polyomino.BlockMap
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun BlockMap.toJson(): JSONObject =
        JSONObject().apply {
            put(BLOCKS, blocks.toJsonArray { toJson() })
        }

/** @throws JSONException */
fun JSONObject.toBlockMap(): BlockMap =
        BlockMap(
                blocks = getJSONArrayAsList(com.ridwanstandingby.ntris.data.adapters.BLOCKS) { getJSONObject(it).toBlock() }.toMutableList()
        )

private const val BLOCKS = "blocks"
