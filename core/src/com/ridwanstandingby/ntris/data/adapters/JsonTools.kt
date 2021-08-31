package com.ridwanstandingby.ntris.data.adapters

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun <T, R> Iterable<T>.toJsonArray(toR: T.() -> R) =
    JSONArray(map { it.toR() })


/** @throws JSONException */
fun <T> JSONObject.getJSONArrayAsList(name: String, getToT: JSONArray.(Int) -> T): List<T> =
    getJSONArray(name).toList(getToT)

/** @throws JSONException */
fun <T> JSONArray.toList(getToT: JSONArray.(Int) -> T): List<T> =
    (0 until length()).map {
        getToT(it)
    }

/** @throws JSONException */
fun String.toJson(): JSONObject =
    JSONObject(this)
