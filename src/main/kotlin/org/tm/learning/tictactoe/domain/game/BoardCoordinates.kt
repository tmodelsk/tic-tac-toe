package org.tm.learning.tictactoe.domain.game

import java.lang.IllegalArgumentException

class BoardCoordinates {
    val col: Char
    val row: Int

    constructor(coordinatesStr: String) {
        if (coordinatesStr.length != 2) {
            throw IllegalArgumentException("Coordinates literal should have length 2")
        }

        val col = coordinatesStr[0]
        val row = coordinatesStr.substring(1, 2).toInt()

        val colUpper = col.toLowerCase()
        if (colUpper != 'a' && colUpper != 'b' && colUpper != 'c') {
            throw IllegalArgumentException("Column should be a,b or c, not $col")
        }

        if (row < 1 || row > 3) {
            throw IllegalArgumentException("Row should be in range [1,3] not $row")
        }

        this.row = row
        this.col = colUpper
    }

    constructor(col: Char, row: Int) : this("" + col + row)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardCoordinates

        if (col != other.col) return false
        if (row != other.row) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col.hashCode()
        return result
    }

    override fun toString(): String {
        return "" + col + row
    }
}