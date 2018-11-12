package com.ridwanstandingby.ntris.data.local.adapters

import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprint
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun PolyominoBlueprint.toJson(): JSONObject =
        JSONObject().apply {
            put(RANK, rank)
            put(INDEX, index)
            put(BLOCK_MATRIX, blockMatrix.toJson())
        }

/** @throws JSONException */
fun JSONObject.toPolyominoBlueprint(): PolyominoBlueprint =
        PolyominoBlueprint(
                rank = getInt(RANK),
                index = getInt(INDEX),
                blockMatrix = getJSONObject(BLOCK_MATRIX).toArray2DBoolean()
        )

private const val RANK = "rank"
private const val INDEX = "index"
private const val BLOCK_MATRIX = "blockMatrix"
