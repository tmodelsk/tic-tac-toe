package org.tm.learning.tictactoe.api

import java.util.*

data class JoinGameResponse(
        val gameId: UUID,
        val gameUrl: String,
        val playerGameToken: UUID
)