package com.ridwanstandingby.ntris.game

import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.events.Clock
import com.ridwanstandingby.ntris.polyomino.BlockMap
import com.ridwanstandingby.ntris.polyomino.PolyominoSpawner
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintLoader

class GameFactory(private val gameDataManager: GameDataManager) {

    fun newGame(): Game {
        val polyominoSpawner = createPolyominoSpawner()
        val score = Score(0, 0)

        return Game(
                gameDataManager = gameDataManager,
                polyominoSpawner = polyominoSpawner,
                clock = Clock(),
                score = score,
                isGameOver = false,
                doRestart = false,
                isPaused = false,
                hasSwappedReserve = false,
                backgroundBlockMap = BlockMap(),
                currentPiece = polyominoSpawner.generatePolyomino(score).apply { setToPlaySpawnPosition() },
                nextPiece = polyominoSpawner.generatePolyomino(score),
                reservePiece = polyominoSpawner.generatePolyomino(score))
    }

    fun fromSavedGame(savedGame: SavedGame) = Game(
            gameDataManager = gameDataManager,
            polyominoSpawner = createPolyominoSpawner(),
            clock = savedGame.clock,
            score = savedGame.score,
            isGameOver = savedGame.isGameOver,
            doRestart = savedGame.doRestart,
            isPaused = savedGame.isPaused,
            hasSwappedReserve = savedGame.hasSwappedReserve,
            backgroundBlockMap = savedGame.backgroundBlockMap,
            currentPiece = savedGame.currentPiece,
            nextPiece = savedGame.nextPiece,
            reservePiece = savedGame.reservePiece)

    private fun createPolyominoSpawner(): PolyominoSpawner {
        val polyominoBlueprintHolder = gameDataManager.polyominoBlueprintHolder
                ?: PolyominoBlueprintLoader().load()
        return PolyominoSpawner(polyominoBlueprintHolder)
    }
}
