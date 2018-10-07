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

    class CurrentPieceReflect : Event({ game ->
        game.currentPieceReflect()
    })

    class SwapReserveAttempt : Event({ game ->
        game.swapReserveAttempt()
    })

    class Pulse : Event({ game ->
        game.pulse()
    })

    class NextRerollAttempt : Event({ game ->
        game.nextRerollAttempt()
    })

    class Exit : Event({ game ->
        game.exit()
    })
}
