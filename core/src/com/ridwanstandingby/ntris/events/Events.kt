package com.ridwanstandingby.ntris.events

object Events {

    class CurrentPieceMoveDown : Event({ game ->
        game.currentPieceMoveDown()
    })

    class CurrentPieceMoveLeft : Event({ game ->
        game.currentPieceMoveLeft()
    })

    class CurrentPieceMoveRight : Event({ game ->
        game.currentPieceMoveRight()
    })

    class CurrentPieceRotateLeft : Event({ game ->
        game.currentPieceRotateLeft()
    })

    class CurrentPieceRotateRight : Event({ game ->
        game.currentPieceRotateRight()
    })

    class Pause : Event({ game ->
        game.togglePause()
    })

    class SwapReserveAttempt : Event({ game ->
        game.swapReserveAttempt()
    })
}
