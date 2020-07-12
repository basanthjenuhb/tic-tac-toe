package org.tictactoe.game.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.player.api.Player;
import org.tictactoe.player.impl.ComputerPlayer;

import java.util.Optional;

class GameEngineTest {

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        ComputerPlayer player1 = new ComputerPlayer();
        ComputerPlayer player2 = new ComputerPlayer();
        player1.assignSymbol(MoveSymbol.CROSS);
        player2.assignSymbol(MoveSymbol.CIRCLE);
        gameEngine = new GameEngine(player1, player2);
    }

    @Test
    void shouldConductGameAndReturnDraw() throws Exception {
        Optional<Player> player = gameEngine.conductGame();
        Assertions.assertThat(player.isEmpty()).isTrue();
    }
}
