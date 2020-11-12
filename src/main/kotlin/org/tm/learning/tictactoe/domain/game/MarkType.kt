package org.tm.learning.tictactoe.domain.game

import java.lang.IllegalStateException

enum class MarkType {
    CROSS, CIRCLE;

    fun opposite(): MarkType {
        return when {
            this == CIRCLE -> CROSS
            this == CROSS -> CIRCLE
            else -> {
                throw IllegalStateException("Not supported!")
            }
        }
    }
}