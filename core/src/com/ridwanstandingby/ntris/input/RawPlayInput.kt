package com.ridwanstandingby.ntris.input

data class RawPlayInput(var moveLeft: Boolean = false,
                        var moveRight: Boolean = false,
                        var moveDown: Boolean = false,
                        var rotateLeft: Boolean = false,
                        var rotateRight: Boolean = false,
                        var pause: Boolean = false,
                        var reserve: Boolean = false,
                        var score: Boolean = false,
                        var next: Boolean = false,
                        var play: Boolean = false) {
    infix fun or(rawPlayInput: RawPlayInput) = RawPlayInput(
            moveLeft || rawPlayInput.moveLeft,
            moveRight || rawPlayInput.moveRight,
            moveDown || rawPlayInput.moveDown,
            rotateLeft || rawPlayInput.rotateLeft,
            rotateRight || rawPlayInput.rotateRight,
            pause || rawPlayInput.pause,
            reserve || rawPlayInput.reserve,
            score || rawPlayInput.score,
            next || rawPlayInput.next,
            play || rawPlayInput.play)
}