package org.tm.learning.tictactoe.domain.game

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.swing.text.Position

class GameEntityTest {

    @Test
    fun `when game started then win result should be in progress`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        val gameResult = game.gameResult()

        // then
        gameResult.shouldBe(GameResult.IN_PROGRESS)
    }

    @Test
    fun `when game started then round number should be first`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        val roundResult = game.roundNumber()

        // then
        roundResult.shouldBe(1)
    }

    @Test
    fun `when game started for circles and mark cross then should throw`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when - then
        shouldThrow<IllegalMarkException> { game.mark("a1", MarkType.CROSS) }
    }

    @Test
    fun `when play circle row win strategy then result should be win for circles`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        game.mark("a1", MarkType.CIRCLE)
        game.mark("a2", MarkType.CROSS)

        game.mark("b1", MarkType.CIRCLE)
        game.mark("b2", MarkType.CROSS)

        game.mark("c1", MarkType.CIRCLE)

        val gameResult = game.gameResult()

        // then
        gameResult.shouldBe(GameResult.WON_CIRCLES)
        game.roundNumber().shouldBe(3)
        game.board().size.shouldBe(5)
    }

    @Test
    fun `when play row win strategy then result should be valid round and board state`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        game.mark("a1", MarkType.CIRCLE)
        game.mark("a2", MarkType.CROSS)

        game.mark("b1", MarkType.CIRCLE)
        game.mark("b2", MarkType.CROSS)

        game.mark("c1", MarkType.CIRCLE)

        // then
        game.roundNumber().shouldBe(3)
        game.board().size.shouldBe(5)
    }

    @Test
    fun `when play cross column win strategy then result should be win for crosses`() {
        // given
        val game = GameEntity(MarkType.CROSS)

        // when
        game.mark("a1", MarkType.CROSS)
        game.mark("b1", MarkType.CIRCLE)

        game.mark("a2", MarkType.CROSS)
        game.mark("b2", MarkType.CIRCLE)

        game.mark("a3", MarkType.CROSS)

        val gameResult = game.gameResult()

        // then
        gameResult.shouldBe(GameResult.WON_CROSSES)
    }

    @Test
    fun `when play circle diagonal left right win strategy then should result be win for circles`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        game.mark("a1")
        game.mark("b1")

        game.mark("b2")
        game.mark("c2")

        game.mark("c3")

        val gameResult = game.gameResult()

        // then
        gameResult.shouldBe(GameResult.WON_CIRCLES)
    }

    @Test
    fun `when play circle diagonal right left win strategy then result should be win for circles`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        game.mark("c1")
        game.mark("b1")

        game.mark("b2")
        game.mark("c2")

        game.mark("a3")

        val gameResult = game.gameResult()

        // then
        assertNotNull(gameResult)
        gameResult.shouldBe(GameResult.WON_CIRCLES)
    }

    @Test
    fun `when play circle row win strategy and mark next move then should throw`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)

        // when
        game.mark("a1", MarkType.CIRCLE)
        game.mark("a2", MarkType.CROSS)

        game.mark("b1", MarkType.CIRCLE)
        game.mark("b2", MarkType.CROSS)

        game.mark("c1", MarkType.CIRCLE)

        // when - then
        assertThrows<GameOverException> { game.mark("c2", MarkType.CROSS) }
    }

    @Test
    fun `when marking already taken position then should throw`() {
        // given
        val game = GameEntity(MarkType.CIRCLE)
        game.mark("a1", MarkType.CIRCLE)

        // when - then
        assertThrows<PositionTakenException> { game.mark("a1", MarkType.CROSS) }
    }
}