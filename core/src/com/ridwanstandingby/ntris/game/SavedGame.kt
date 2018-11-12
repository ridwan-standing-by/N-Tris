package com.ridwanstandingby.ntris.game

import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.polyomino.BlockMap
import com.ridwanstandingby.ntris.polyomino.Polyomino

data class SavedGame(val clock: Clock,
                     val score: Score,
                     val isGameOver: Boolean,
                     val doRestart: Boolean,
                     val isPaused: Boolean,
                     val backgroundBlockMap: BlockMap,
                     val currentPiece: Polyomino,
                     val nextPiece: Polyomino,
                     val reservePiece: Polyomino,
                     val hasSwappedReserve: Boolean)
