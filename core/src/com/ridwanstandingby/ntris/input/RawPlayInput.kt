package com.ridwanstandingby.ntris.input

data class RawPlayInput(
    var moveLeft: Boolean = false,
    var moveRight: Boolean = false,
    var moveDown: Boolean = false,
    var rotateLeft: Boolean = false,
    var rotateRight: Boolean = false,
    var reflect: Boolean = false,
    var reserve: Boolean = false,
    var score: Boolean = false,
    var next: Boolean = false,
    var play: Boolean = false,
    var pause: Boolean = false,
    var pauseResume: Boolean = false,
    var pauseRestart: Boolean = false,
    var gameOver: Boolean = false,
    var back: Boolean = false
) {
    infix fun or(rawPlayInput: RawPlayInput) = RawPlayInput(
        moveLeft || rawPlayInput.moveLeft,
        moveRight || rawPlayInput.moveRight,
        moveDown || rawPlayInput.moveDown,
        rotateLeft || rawPlayInput.rotateLeft,
        rotateRight || rawPlayInput.rotateRight,
        reflect || rawPlayInput.reflect,
        reserve || rawPlayInput.reserve,
        score || rawPlayInput.score,
        next || rawPlayInput.next,
        play || rawPlayInput.play,
        pause || rawPlayInput.pause,
        pauseResume || rawPlayInput.pauseResume,
        pauseRestart || rawPlayInput.pauseRestart,
        gameOver || rawPlayInput.gameOver,
        back || rawPlayInput.back
    )
}
