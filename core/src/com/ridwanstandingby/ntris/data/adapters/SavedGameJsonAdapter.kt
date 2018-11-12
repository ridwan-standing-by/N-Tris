package com.ridwanstandingby.ntris.data.adapters

import com.ridwanstandingby.ntris.game.SavedGame
import org.json.JSONException
import org.json.JSONObject

/** @throws JSONException */
fun SavedGame.toJson(): JSONObject =
        JSONObject().apply {
            put(CLOCK, clock.toJson())
            put(SCORE, score.toJson())
            put(IS_GAME_OVER, isGameOver)
            put(DO_RESTART, doRestart)
            put(IS_PAUSED, isPaused)
            put(BACKGROUND_BLOCK_MAP, backgroundBlockMap.toJson())
            put(CURRENT_PIECE, currentPiece.toJson())
            put(NEXT_PIECE, nextPiece.toJson())
            put(RESERVE_PIECE, reservePiece.toJson())
            put(HAS_SWAPPED_RESERVE, hasSwappedReserve)
        }

/** @throws JSONException */
fun JSONObject.toSavedGame(): SavedGame =
        SavedGame(
                clock = getJSONObject(com.ridwanstandingby.ntris.data.adapters.CLOCK).toClock(),
                score = getJSONObject(com.ridwanstandingby.ntris.data.adapters.SCORE).toScore(),
                isGameOver = getBoolean(com.ridwanstandingby.ntris.data.adapters.IS_GAME_OVER),
                doRestart = getBoolean(com.ridwanstandingby.ntris.data.adapters.DO_RESTART),
                isPaused = getBoolean(com.ridwanstandingby.ntris.data.adapters.IS_PAUSED),
                backgroundBlockMap = getJSONObject(com.ridwanstandingby.ntris.data.adapters.BACKGROUND_BLOCK_MAP).toBlockMap(),
                currentPiece = getJSONObject(com.ridwanstandingby.ntris.data.adapters.CURRENT_PIECE).toPolyomino(),
                nextPiece = getJSONObject(com.ridwanstandingby.ntris.data.adapters.NEXT_PIECE).toPolyomino(),
                reservePiece = getJSONObject(com.ridwanstandingby.ntris.data.adapters.RESERVE_PIECE).toPolyomino(),
                hasSwappedReserve = getBoolean(com.ridwanstandingby.ntris.data.adapters.HAS_SWAPPED_RESERVE)
        )

private const val CLOCK = "clock"
private const val SCORE = "score"
private const val IS_GAME_OVER = "isGameOver"
private const val DO_RESTART = "doRestart"
private const val IS_PAUSED = "isPaused"
private const val BACKGROUND_BLOCK_MAP = "backgroundBlockMap"
private const val CURRENT_PIECE = "currentPiece"
private const val NEXT_PIECE = "nextPiece"
private const val RESERVE_PIECE = "reservePiece"
private const val HAS_SWAPPED_RESERVE = "hasSwappedReserve"
