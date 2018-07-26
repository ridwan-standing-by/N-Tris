package com.ridwanstandingby.ntris.game

data class Score(var points: Int, var lines: Int) {

    fun incrementScore(numberOfLines: Int) {
        lines += numberOfLines
        points += GameRules.linesToScore(numberOfLines)
    }
}
