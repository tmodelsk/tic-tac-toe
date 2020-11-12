package org.tm.learning.tictactoe.domain.game

import java.lang.RuntimeException

class PositionTakenException : RuntimeException {
    constructor(boardCoordinates: BoardCoordinates) : super("Position $boardCoordinates is taken!")
}