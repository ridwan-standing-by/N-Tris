package com.ridwanstandingby.ntris.polyomino

import com.badlogic.gdx.graphics.Color
import com.ridwanstandingby.ntris.game.Score
import com.ridwanstandingby.ntris.polyomino.PolyominoConstants.MIN_RANK
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder
import com.ridwanstandingby.ntris.polyomino.blueprint.PolyominoBlueprintHolder.Companion.rankToIndex
import java.lang.Math.pow
import java.util.*
import kotlin.math.roundToInt

class PolyominoSpawner(private val polyominoBlueprintHolder: PolyominoBlueprintHolder) {

    private val random = Random()

    fun generatePolyomino(score: Score): Polyomino {
        val rank = scoreToRank(score)
        val index = randomIndex(rank)
        val colour = randomColour()
        val blueprint = polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(rank)][index]
        return Polyomino(blueprint, colour)
    }

    private fun scoreToRank(score: Score): Int =
            when {
                score.lines < 5 -> randomRankDifficultyIndex(0)
                score.lines < 15 -> randomRankDifficultyIndex(1)
                score.lines < 30 -> randomRankDifficultyIndex(2)
                score.lines < 50 -> randomRankDifficultyIndex(3)
                score.lines < 75 -> randomRankDifficultyIndex(4)
                score.lines < 105 -> randomRankDifficultyIndex(5)
                score.lines < 140 -> randomRankDifficultyIndex(6)
                else -> randomRankDifficultyIndex(7)
            }

    private fun randomRankDifficultyIndex(d: Int): Int {
        when (d) {
            0 -> return 4
            1 -> {
                val r = random.nextInt(8)
                return when (r) {
                    0 -> 5
                    else -> 4
                }
            }
            else -> {
                val min = MIN_RANK
                val max = MIN_RANK + d - 1
                val N = twoToThe(d)
                val r = random.nextInt(N)
                (max downTo min).forEach {
                    if (r < twoToThe(max - it + 1)) return it
                }
                return min
            }
        }
    }

    private fun randomIndex(rank: Int) = random.nextInt(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(rank)].size)

    private fun randomColour(): Color {
        val H = random.nextFloat() * 360f
        val S = 1.0f
        val V = 1.0f

        val C = V * S  // Chroma, from 0 to 1
        val Hp = H / 60  // Sector, from 0 to 6
        val term = (Hp % 2) - 1
        val X = if (term >= 0)
            C * (1 - term)
        else
            C * (1 + term)

        val (R, G, B) = when {
            Hp < 1f -> arrayOf(C, X, 0f)
            Hp < 2f -> arrayOf(X, C, 0f)
            Hp < 3f -> arrayOf(0f, C, X)
            Hp < 4f -> arrayOf(0f, X, C)
            Hp < 5f -> arrayOf(X, 0f, C)
            Hp < 6f -> arrayOf(C, 0f, X)
            else -> arrayOf(0f, 0f, 0f)
        }

        val m = V - C
        return Color(R + m, G + m, B + m, 1f)
    }

    companion object {
        private fun twoToThe(n: Int) = pow(2.toDouble(), n.toDouble()).roundToInt()
    }
}
