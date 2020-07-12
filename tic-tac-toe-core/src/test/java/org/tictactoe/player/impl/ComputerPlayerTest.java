package org.tictactoe.player.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tictactoe.game.api.EngineGameState;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.game.impl.GameStateImpl;

class ComputerPlayerTest {

    private ComputerPlayer computerPlayer;
    private EngineGameState engineGameState;

    @BeforeEach
    void setUp() {
        computerPlayer = new ComputerPlayer();
        engineGameState = new GameStateImpl(3);
    }

    @Test
    void chechTheFirstMoveIs5() {
        computerPlayer.assignSymbol(MoveSymbol.CROSS);
        int move = computerPlayer.makeMove(engineGameState);
        Assertions.assertThat(move).isEqualTo(5);
    }

    @Test
    void shouldChooseTheNextWinningStepIfExists() throws Exception {
        computerPlayer.assignSymbol(MoveSymbol.CROSS);
        engineGameState.setSymbolAtPosition(1, MoveSymbol.CROSS);
        engineGameState.setSymbolAtPosition(2, MoveSymbol.CROSS);
        int move = computerPlayer.makeMove(engineGameState);
        Assertions.assertThat(move).isEqualTo(3);
    }

    @Test
    void shouldAvoidNextWinningMoveByOpponent() throws Exception {
        computerPlayer.assignSymbol(MoveSymbol.CROSS);
        engineGameState.setSymbolAtPosition(1, MoveSymbol.CIRCLE);
        engineGameState.setSymbolAtPosition(2, MoveSymbol.CIRCLE);
        int move = computerPlayer.makeMove(engineGameState);
        Assertions.assertThat(move).isEqualTo(3);
    }
}
