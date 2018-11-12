package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.polyomino.Polyomino
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun Polyomino.toJson(): JSONObject =
        JSONObject().apply {
            put(POLYOMINO_BLUEPRINT, polyominoBlueprint.toJson())
            put(COLOUR, colour.toJson())
            put(POSITION, position.toJson())
            put(RELATIVE_COORDINATES, relativeCoordinates.toJsonArray { toJson() })
        }

/** @throws JSONException */
fun JSONObject.toPolyomino(): Polyomino =
        Polyomino(
                polyominoBlueprint = getJSONObject(com.ridwanstandingby.ntris.data.adapters.POLYOMINO_BLUEPRINT).toPolyominoBlueprint(),
                colour = getJSONObject(com.ridwanstandingby.ntris.data.adapters.COLOUR).toColor(),
                position = getJSONObject(com.ridwanstandingby.ntris.data.adapters.POSITION).toVector2(),
                relativeCoordinates = getJSONArrayAsList(com.ridwanstandingby.ntris.data.adapters.RELATIVE_COORDINATES) { getJSONObject(it).toVector2() }.toSet()
        )

private const val POLYOMINO_BLUEPRINT = "polyominoBlueprint"
private const val COLOUR = "colour"
private const val POSITION = "position"
private const val RELATIVE_COORDINATES = "relativeCoordinates"
