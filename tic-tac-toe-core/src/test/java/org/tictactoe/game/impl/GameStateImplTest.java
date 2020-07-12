package org.tictactoe.game.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tictactoe.exceptions.IllegalMoveException;
import org.tictactoe.exceptions.IllegalPositionException;
import org.tictactoe.game.api.MoveSymbol;

class GameStateImplTest {

    private GameStateImpl gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameStateImpl(3);
    }

    @Test
    void initialPositionsShouldBeEmpty() throws Exception {
        MoveSymbol symbolAtPosition = gameState.getSymbolAtPosition(1);
        Assertions.assertThat(symbolAtPosition).isEqualByComparingTo(MoveSymbol.EMPTY);
    }

    @Test
    void shouldAssignPositions() throws Exception {
        gameState.setSymbolAtPosition(1, MoveSymbol.CIRCLE);
        MoveSymbol symbolAtPosition = gameState.getSymbolAtPosition(1);
        Assertions.assertThat(symbolAtPosition).isEqualByComparingTo(MoveSymbol.CIRCLE);
    }

    @Test
    void shouldThrowExceptionIfAssigningNullSymbol() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            gameState.setSymbolAtPosition(1, null);
        }).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    void shouldThrowExceptionIfUsingIllegalPosition() {
        Assertions.assertThatThrownBy(() -> {
            gameState.setSymbolAtPosition(10, MoveSymbol.CIRCLE);
        }).isInstanceOf(IllegalPositionException.class);
    }
}
