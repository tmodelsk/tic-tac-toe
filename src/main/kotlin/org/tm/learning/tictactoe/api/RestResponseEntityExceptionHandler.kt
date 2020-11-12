package org.tm.learning.tictactoe.api

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.tm.learning.tictactoe.domain.api.GameNotFoundException
import org.tm.learning.tictactoe.domain.api.PlayerAlreadyConfirmedException
import org.tm.learning.tictactoe.domain.api.PlayersNotConfirmedException
import org.tm.learning.tictactoe.domain.game.GameOverException
import org.tm.learning.tictactoe.domain.game.IllegalMarkException
import org.tm.learning.tictactoe.domain.game.PositionTakenException
import org.tm.learning.tictactoe.domain.networkgame.PlayerTokenNotFoundException

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [GameNotFoundException::class])
    protected fun handleNotFound(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message, HttpHeaders(), HttpStatus.NOT_FOUND, request!!)
    }

    @ExceptionHandler(value = [
        PlayersNotConfirmedException::class, PlayerAlreadyConfirmedException::class,
        PlayerTokenNotFoundException::class])
    protected fun handleForbidden(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message, HttpHeaders(), HttpStatus.FORBIDDEN, request!!)
    }

    @ExceptionHandler(value = [IllegalMarkException::class, PositionTakenException::class, GameOverException::class])
    protected fun handleBadRequest(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message, HttpHeaders(), HttpStatus.BAD_REQUEST, request!!)
    }
}