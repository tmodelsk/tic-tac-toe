package org.tm.learning.tictactoe.api

import org.tm.learning.tictactoe.domain.game.MarkType
import java.util.*

data class MarkRequest(var playerToken: UUID, var col: Char, var row: Int)