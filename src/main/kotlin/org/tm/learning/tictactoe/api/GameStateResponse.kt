package org.tm.learning.tictactoe.api

import org.tm.learning.tictactoe.domain.api.NetworkGameStatus
import org.tm.learning.tictactoe.domain.game.MarkType
import java.util.*

data class GameStateResponse(
        val gameId: UUID,
        val status: NetworkGameStatus,
        val playerOneMarkType: MarkType,
        val playerTwoMarkType: MarkType,
        val playerSubRound: Int,
        val roundNumber: Int,
        val board: List<GameBoardMarkResponse>
)

data class GameBoardMarkResponse(val col: Char, val row: Int, val mark: MarkType)