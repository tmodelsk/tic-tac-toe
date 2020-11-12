package org.tm.learning.tictactoe.domain.networkgame

import org.tm.learning.tictactoe.domain.game.MarkType

data class PlayerId(
        val markType: MarkType,
        val gameToken: PlayerToken
)