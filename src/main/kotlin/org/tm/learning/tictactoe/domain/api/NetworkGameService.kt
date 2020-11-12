package org.tm.learning.tictactoe.domain.api

import org.springframework.stereotype.Service
import org.tm.learning.tictactoe.domain.game.BoardCoordinates
import org.tm.learning.tictactoe.domain.game.MarkType
import org.tm.learning.tictactoe.domain.networkgame.GameId
import org.tm.learning.tictactoe.domain.networkgame.NetworkGameEntity
import org.tm.learning.tictactoe.domain.networkgame.PlayerToken
import java.lang.IllegalStateException

@Service
class NetworkGameService {

    private val gamesMap: MutableMap<GameId, NetworkGameEntity> = mutableMapOf()

    fun startNewGame(): NewGameResult {
        val networkGame = NetworkGameEntity(MarkType.CROSS)
        gamesMap.put(networkGame.gameId, networkGame)

        networkGame.playerOne.confirmPlayer()

        return NewGameResult(networkGame.gameId, networkGame.playerOne.playerId, networkGame.playerTwo.playerId)
    }

    fun joinGame(gameId: GameId): JoinGameResult {
        val game = fetchGameOrThrow(gameId)

        if (game.arePlayersConfirmed()) {
            throw PlayerAlreadyConfirmedException("Players are already confirmed for game ${gameId}!")
        }

        game.playerTwo.confirmPlayer()

        return JoinGameResult(game.gameId, game.playerTwo.playerId)
    }

    fun gameInfo(gameId: GameId): NetworkGameInfo {
        return fetchGameOrThrow(gameId).gameInfo()
    }

    fun mark(gameId: GameId, playerToken: PlayerToken, coordinates: BoardCoordinates) {
        val game = fetchGameOrThrow(gameId)

        if (!game.arePlayersConfirmed()) {
            throw PlayersNotConfirmedException("Players are not confirmed for game ${gameId}!")
        }

        game.mark(playerToken, coordinates)
    }


    private fun fetchGameOrThrow(gameId: GameId): NetworkGameEntity {
        return gamesMap[gameId] ?: throw GameNotFoundException(gameId)
    }
}