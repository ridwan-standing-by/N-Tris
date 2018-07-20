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
                        var play: Boolean = false)
