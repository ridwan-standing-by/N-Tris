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
                val n = twoToThe(d)
                val r = random.nextInt(n)
                (max downTo min).forEach {
                    if (r < twoToThe(max - it + 1)) return it
                }
                return min
            }
        }
    }

    private fun randomIndex(rank: Int) = random.nextInt(polyominoBlueprintHolder.polyominoBlueprints[rankToIndex(rank)].size)

    private fun randomColour(): Color {
        val hue = random.nextFloat() * 360f
        val saturation = 1.0f
        val value = 1.0f

        val chroma = value * saturation  // Chroma, from 0 to 1
        val sector = hue / 60  // Sector, from 0 to 6
        val term = (sector % 2) - 1
        val x = if (term >= 0)
            chroma * (1 - term)
        else
            chroma * (1 + term)

        val (red, green, blue) = when {
            sector < 1f -> arrayOf(chroma, x, 0f)
            sector < 2f -> arrayOf(x, chroma, 0f)
            sector < 3f -> arrayOf(0f, chroma, x)
            sector < 4f -> arrayOf(0f, x, chroma)
            sector < 5f -> arrayOf(x, 0f, chroma)
            sector < 6f -> arrayOf(chroma, 0f, x)
            else -> arrayOf(0f, 0f, 0f)
        }

        val m = value - chroma
        return Color(red + m, green + m, blue + m, 1f)
    }

    companion object {
        private fun twoToThe(n: Int) = pow(2.toDouble(), n.toDouble()).roundToInt()
    }
}
