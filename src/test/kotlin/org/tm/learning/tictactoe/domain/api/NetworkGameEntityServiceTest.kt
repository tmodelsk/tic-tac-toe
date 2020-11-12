package org.tm.learning.tictactoe.domain.api

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.tm.learning.tictactoe.domain.game.BoardCoordinates
import org.tm.learning.tictactoe.domain.networkgame.GameId
import java.lang.IllegalStateException
import java.lang.RuntimeException

class NetworkGameEntityServiceTest {

    @Test
    fun `joining by unknown gameId should throw`() {
        // given
        val service = create()

        // when - then
        shouldThrow<GameNotFoundException> { service.joinGame(GameId.random()) }
    }

    @Test
    fun `start new game and join by gameId should return proper results`() {
        // given
        val service = create()
        val newGameResult = service.startNewGame()

        // when
        val joinResult = service.joinGame(newGameResult.gameId)

        // then
        joinResult.gameId.shouldBe(newGameResult.gameId)
    }

    @Test
    fun `start new game and join twice should return proper results`() {
        // given
        val service = create()
        val newGameResult = service.startNewGame()
        val joinResult = service.joinGame(newGameResult.gameId)

        // when
        assertThrows<PlayerAlreadyConfirmedException> { service.joinGame(newGameResult.gameId) }


        // then
        joinResult.gameId.shouldBe(newGameResult.gameId)
    }

    @Test
    fun `start new game should return waiting for player info `() {
        // given
        val service = create()
        val newGameResult = service.startNewGame()

        // when
        val gameInfo = service.gameInfo(newGameResult.gameId)

        // then
        gameInfo.gameId.shouldBe(newGameResult.gameId)
        gameInfo.status.shouldBe(NetworkGameStatus.WAITING_FOR_PLAYERS)
    }

    @Test
    fun `start new game and join should return in progress info`() {
        // given
        val service = create()
        val newGameResult = service.startNewGame()
        service.joinGame(newGameResult.gameId)

        // when
        val gameInfo = service.gameInfo(newGameResult.gameId)

        // then
        gameInfo.gameId.shouldBe(newGameResult.gameId)
        gameInfo.status.shouldBe(NetworkGameStatus.IN_PROGRESS)
        gameInfo.playerSubRound.shouldBe(1)
    }

    @Test
    fun `start new game and join and mark should result in game change`() {
        // given
        val service = create()
        val newGameResult = service.startNewGame()
        service.joinGame(newGameResult.gameId)

        // when
        service.mark(
                newGameResult.gameId,
                newGameResult.playerOneId.gameToken,
                BoardCoordinates("a1"))

        // then
        val gameInfo = service.gameInfo(newGameResult.gameId)

        gameInfo.gameId.shouldBe(newGameResult.gameId)
        gameInfo.status.shouldBe(NetworkGameStatus.IN_PROGRESS)
        gameInfo.playerSubRound.shouldBe(2)
        gameInfo.roundNumber.shouldBe(1)

        val board = gameInfo.gameBoard
        board.shouldHaveSize(1)
    }

    private fun create() = NetworkGameService()
}