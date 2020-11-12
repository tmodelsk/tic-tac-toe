package org.tm.learning.tictactoe.api

import java.util.*

data class StartGameResponse(
        val gameId: UUID,
        val gameUrl: String,
        val playerOneGameToken: UUID,
        val joinGameUrl: String
)