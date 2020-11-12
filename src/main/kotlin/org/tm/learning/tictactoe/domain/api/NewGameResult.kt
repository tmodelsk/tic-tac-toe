package org.tm.learning.tictactoe.domain.api

import org.tm.learning.tictactoe.domain.networkgame.GameId
import org.tm.learning.tictactoe.domain.networkgame.PlayerId

data class NewGameResult(
        val gameId: GameId,
        val playerOneId: PlayerId,
        val playerTwoId: PlayerId
)