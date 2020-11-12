package org.tm.learning.tictactoe.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.tm.learning.tictactoe.domain.api.NetworkGameService
import org.tm.learning.tictactoe.domain.game.BoardCoordinates
import org.tm.learning.tictactoe.domain.networkgame.GameId
import org.tm.learning.tictactoe.domain.networkgame.PlayerToken
import java.util.*

@RestController
@RequestMapping("/game")
class GameRestResource(
        private val networkGameService: NetworkGameService) {


    @PostMapping
    fun startNewGame(): ResponseEntity<StartGameResponse> {
        val newGameInfo = networkGameService.startNewGame()

        val gameUrl = gameUrl(newGameInfo.gameId)

        return ResponseEntity(StartGameResponse(
                newGameInfo.gameId.value,
                gameUrl,
                newGameInfo.playerOneId.gameToken.value,
                "$gameUrl/join"),
                HttpStatus.CREATED
        )
    }

    @PostMapping("/{gameId}/join")
    fun joinGame(@PathVariable("gameId") gameId: UUID): JoinGameResponse {
        val joinResult = networkGameService.joinGame(GameId(gameId))

        return JoinGameResponse(
                joinResult.gameId.value,
                gameUrl(joinResult.gameId),
                joinResult.playerId.gameToken.value
        )
    }

    @GetMapping("/{gameId}")
    fun getGameState(@PathVariable("gameId") gameId: UUID): GameStateResponse {

        val gameInfo = networkGameService.gameInfo(GameId(gameId))

        return GameStateResponse(
                gameId = gameInfo.gameId.value,
                status = gameInfo.status,
                playerSubRound = gameInfo.playerSubRound,
                roundNumber = gameInfo.roundNumber,
                playerOneMarkType = gameInfo.playerOneMarkType,
                playerTwoMarkType = gameInfo.playerTwoMarkType,
                board = gameInfo.gameBoard.map { bm -> GameBoardMarkResponse(bm.boardCoordinates.col, bm.boardCoordinates.row, bm.markType) }
        )
    }

    @PostMapping("/{gameId}")
    fun makeMove(@PathVariable("gameId") gameId: UUID, @RequestBody markRequest: MarkRequest): ResponseEntity<Any> {
        networkGameService.mark(
                GameId(gameId),
                PlayerToken(markRequest.playerToken),
                BoardCoordinates(markRequest.col, markRequest.row)
        )

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    private fun gameUrl(gameId: GameId): String {
        return "/game/${gameId.value}"
    }
}