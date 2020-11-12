package org.tm.learning.tictactoe.domain.game

import java.lang.RuntimeException

class GameOverException : RuntimeException {
    constructor(gameResult: GameResult) : super("Game is over: $gameResult")
}