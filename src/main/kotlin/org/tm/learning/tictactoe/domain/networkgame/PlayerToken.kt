package org.tm.learning.tictactoe.domain.networkgame

import java.util.*

data class PlayerToken(val value: UUID) {
    override fun toString(): String {
        return value.toString()
    }
}