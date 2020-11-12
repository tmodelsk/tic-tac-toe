package org.tm.learning.tictactoe.domain.api

import org.tm.learning.tictactoe.domain.networkgame.GameId
import org.tm.learning.tictactoe.domain.networkgame.PlayerId

data class JoinGameResult(val gameId: GameId, val playerId: PlayerId)