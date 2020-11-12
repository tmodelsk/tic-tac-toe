package org.tm.learning.tictactoe.domain.api

import java.lang.RuntimeException

class PlayerAlreadyConfirmedException : RuntimeException {
    constructor(message: String?) : super(message)
}