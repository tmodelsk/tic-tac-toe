package org.tm.learning.tictactoe.domain.api

import java.lang.RuntimeException

class PlayersNotConfirmedException : RuntimeException {

    constructor(message: String?) : super(message)
}