package org.tm.learning.tictactoe.domain.networkgame

import org.tm.learning.tictactoe.domain.game.MarkType
import java.util.*

class PlayerEntity(markType: MarkType) {
    val playerId: PlayerId = PlayerId(markType, PlayerToken(UUID.randomUUID()))

    var confirmed: Boolean = false
    private set


    fun confirmPlayer() {
        confirmed = true
    }
}