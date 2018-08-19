package com.ridwanstandingby.ntris.game

data class Score(var points: Int, var lines: Int) {

    operator fun plus(other: Score) = Score(this.points + other.points, this.lines + other.lines)

    operator fun compareTo(other: Score) = this.points - other.points
}
