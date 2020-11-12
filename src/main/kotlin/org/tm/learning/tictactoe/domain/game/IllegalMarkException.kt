package org.tm.learning.tictactoe.domain.game

import java.lang.RuntimeException

class IllegalMarkException : RuntimeException {

    constructor(expectedMarkType: MarkType, currentMarkType: MarkType)
            : super("Expected mark type $expectedMarkType not $currentMarkType!")
}