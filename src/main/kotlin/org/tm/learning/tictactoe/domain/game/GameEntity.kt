package org.tm.learning.tictactoe.domain.game

class GameEntity(firstMarkType: MarkType) {
    private val marks: MutableMap<BoardCoordinates, MarkType> = mutableMapOf()
    var nextMark: MarkType = firstMarkType
    private set

    fun mark(coordinates: String) {
        mark(BoardCoordinates(coordinates), nextMark)
    }

    fun mark(coordinates: String, markType: MarkType) {
        mark(BoardCoordinates(coordinates), markType)
    }

    @Synchronized
    fun mark(boardCoordinates: BoardCoordinates, markType: MarkType) {
        if (marks.containsKey(boardCoordinates)) {
            throw PositionTakenException(boardCoordinates)
        }
        val gameResult = gameResult()
        if (gameResult != GameResult.IN_PROGRESS) {
            throw GameOverException(gameResult)
        }
        if (nextMark != markType) {
            throw IllegalMarkException(nextMark, markType)
        }

        marks.put(boardCoordinates, markType)
        nextMark = markType.opposite()
    }

    private fun getMark(coordinates: String): MarkType? {
        return marks[BoardCoordinates(coordinates)]
    }

    private fun checkWin(markType: MarkType, row: Int): Boolean {
        return marks.entries
                .filter { e -> e.value == markType && e.key.row == row }
                .size == 3
    }

    private fun checkWin(markType: MarkType, column: Char): Boolean {
        return marks.entries
                .filter { e -> e.value == markType && e.key.col == column.toLowerCase() }
                .size == 3
    }

    private fun checkWin(markType: MarkType): Boolean {
        return checkWin(markType, 1) || checkWin(markType, 2) || checkWin(markType, 3)
                ||
                checkWin(markType, 'a') || checkWin(markType, 'b') || checkWin(markType, 'c')
                ||
                (getMark("b2") == markType &&
                        (getMark("a1") == markType && getMark("c3") == markType
                                ||
                                getMark("c1") == markType && getMark("a3") == markType))
    }

    fun gameResult(): GameResult {
        return when {
            checkWin(MarkType.CROSS) -> GameResult.WON_CROSSES
            checkWin(MarkType.CIRCLE) -> GameResult.WON_CIRCLES
            marks.size == 9 -> GameResult.STALEMATE
            else -> GameResult.IN_PROGRESS
        }
    }

    fun roundNumber(): Int {
        return (marks.size / 2)  +1
    }

    fun board(): List<BoardMark> {
        return marks.entries.map { e -> BoardMark(e.value, e.key) }.toList()
    }
}