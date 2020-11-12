package org.tm.learning.tictactoe.domain.api

enum class NetworkGameStatus {
    WAITING_FOR_PLAYERS,
    IN_PROGRESS,
    WON_CIRCLES,
    WON_CROSSES,
    STALEMATE
}