package com.ridwanstandingby.ntris.data.local.adapters

import com.ridwanstandingby.ntris.polyomino.BlockMap
import com.ridwanstandingby.ntris.tools.getJSONArrayAsList
import com.ridwanstandingby.ntris.tools.toJsonArray
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
                blocks = getJSONArrayAsList(BLOCKS) { getJSONObject(it).toBlock() }.toMutableList()
        )

private const val BLOCKS = "blocks"
