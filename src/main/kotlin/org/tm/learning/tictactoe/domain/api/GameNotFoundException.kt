package org.tm.learning.tictactoe.domain.api

import org.tm.learning.tictactoe.domain.networkgame.GameId
import java.lang.RuntimeException

class GameNotFoundException : RuntimeException {
    constructor() : super()
    constructor(gameId: GameId) : super("GameId '${gameId.value}' not found!")
}