package org.tm.learning.tictactoe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicTacToeSrvApplication

fun main(args: Array<String>) {
	runApplication<TicTacToeSrvApplication>(*args)
}
