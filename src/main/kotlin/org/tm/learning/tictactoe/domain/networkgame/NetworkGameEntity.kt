package org.tm.learning.tictactoe.domain.networkgame

import org.tm.learning.tictactoe.domain.api.NetworkGameInfo
import org.tm.learning.tictactoe.domain.api.NetworkGameStatus
import org.tm.learning.tictactoe.domain.game.BoardCoordinates
import org.tm.learning.tictactoe.domain.game.GameEntity
import org.tm.learning.tictactoe.domain.game.GameResult
import org.tm.learning.tictactoe.domain.game.MarkType
import java.lang.IllegalStateException
import java.util.*

class NetworkGameEntity(firstMarkType: MarkType) {
    val gameId: GameId
    val gameEntity: GameEntity

    val playerOne: PlayerEntity
    val playerTwo: PlayerEntity

    init {
        gameId = GameId(UUID.randomUUID())
        gameEntity = GameEntity(firstMarkType)

        playerOne = PlayerEntity((firstMarkType))
        playerTwo = PlayerEntity((firstMarkType.opposite()))
    }

    fun mark(playerToken: PlayerToken, coordinates: BoardCoordinates) {
        val playerId = playerIdBy(playerToken)
        gameEntity.mark(coordinates, playerId.markType)
    }

    fun gameInfo(): NetworkGameInfo {

        val gameResult = gameEntity.gameResult()

        val gameStatus = when {
            !arePlayersConfirmed() -> NetworkGameStatus.WAITING_FOR_PLAYERS
            gameResult == GameResult.IN_PROGRESS -> NetworkGameStatus.IN_PROGRESS
            gameResult == GameResult.WON_CROSSES -> NetworkGameStatus.WON_CROSSES
            gameResult == GameResult.WON_CIRCLES -> NetworkGameStatus.WON_CIRCLES
            gameResult == GameResult.STALEMATE -> NetworkGameStatus.STALEMATE
            else -> throw IllegalStateException("State not supported")
        }

        return NetworkGameInfo(
                gameId = gameId,
                status = gameStatus,
                playerOneMarkType = playerOne.playerId.markType,
                playerTwoMarkType = playerTwo.playerId.markType,
                playerSubRound = if (gameEntity.nextMark == playerOne.playerId.markType) 1 else 2,
                roundNumber = gameEntity.roundNumber(),
                gameBoard = gameEntity.board()
        )
    }

    fun arePlayersConfirmed(): Boolean {
        return playerOne.confirmed && playerTwo.confirmed
    }

    private fun playerIdBy(playerToken: PlayerToken): PlayerId {
        if (playerOne.confirmed && playerOne.playerId.gameToken == playerToken) {
            return playerOne.playerId
        }
        if (playerTwo.confirmed && playerTwo.playerId.gameToken == playerToken) {
            return playerTwo.playerId
        }

        throw PlayerTokenNotFoundException(playerToken)
    }
}