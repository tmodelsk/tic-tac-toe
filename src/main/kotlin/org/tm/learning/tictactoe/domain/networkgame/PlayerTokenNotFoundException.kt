package org.tm.learning.tictactoe.domain.networkgame

import java.lang.RuntimeException

class PlayerTokenNotFoundException : RuntimeException {

    constructor(playerToken: PlayerToken) : super("Player token (confirmed) $playerToken not found!")
}