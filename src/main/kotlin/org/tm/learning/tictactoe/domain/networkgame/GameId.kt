package org.tm.learning.tictactoe.domain.networkgame

import java.util.*

data class GameId(val value: UUID) {
    companion object {
        fun random() = GameId(UUID.randomUUID())
    }

    override fun toString(): String {
        return value.toString()
    }
}