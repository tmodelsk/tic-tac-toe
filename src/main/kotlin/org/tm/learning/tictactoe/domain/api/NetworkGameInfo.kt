package org.tm.learning.tictactoe.domain.api

import org.tm.learning.tictactoe.domain.game.BoardMark
import org.tm.learning.tictactoe.domain.game.MarkType
import org.tm.learning.tictactoe.domain.networkgame.GameId

data class NetworkGameInfo(
        val gameId: GameId,
        val status: NetworkGameStatus,
        val playerOneMarkType: MarkType,
        val playerTwoMarkType: MarkType,
        val playerSubRound: Int,
        val roundNumber: Int,
        val gameBoard: List<BoardMark>
)