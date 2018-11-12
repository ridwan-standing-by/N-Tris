package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.polyomino.geometry.Array2D
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Array2D<Boolean?>.toJson(): JSONObject =
        JSONObject().apply {
            put(ARRAY, array.asList().toJsonArray {
                asList().toJsonArray {
                    this
                }
            })
        }

/** @throws JSONException */
fun JSONObject.toArray2DBoolean(): Array2D<Boolean?> =
        Array2D(
                array = getJSONArrayAsList(com.ridwanstandingby.ntris.data.adapters.ARRAY) { outerIndex ->
                    this.getJSONArray(outerIndex).toList { innerIndex ->
                        try {
                            this.getBoolean(innerIndex)
                        } catch (e: JSONException) {
                            null
                        }
                    }.toTypedArray()
                }.toTypedArray()
        )

private const val ARRAY = "array"
