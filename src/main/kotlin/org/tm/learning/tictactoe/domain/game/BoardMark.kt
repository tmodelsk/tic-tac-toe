package org.tm.learning.tictactoe.domain.game

data class BoardMark(val markType: MarkType, val boardCoordinates: BoardCoordinates) {
    override fun toString(): String {
        return "$boardCoordinates $markType"
    }
}