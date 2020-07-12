package org.tictactoe.game.impl;

import org.tictactoe.game.api.EngineGameState;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.player.api.Player;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bjenuhb
 */

@Slf4j
class GameEngine {

    private Player player1, player2;
    private EngineGameState engineGameState;

    public GameEngine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.engineGameState = new GameStateImpl(3);
    }

    public void displayGameState() {
        engineGameState.displayState();
    }

    /**
     * Makes a move for a particular players
     * @param player
     * @param engineGameState
     * @param moveSymbol
     */
    private void handlePlayerMove(Player player, EngineGameState engineGameState, MoveSymbol moveSymbol) {
        engineGameState.displayState();
        log.info("Player {}'s move", player.getName());
        int latestPosition = 0;
        while (true) {
            latestPosition = player.makeMove(engineGameState);
            try {
                engineGameState.setSymbolAtPosition(latestPosition, moveSymbol);
                break;
            } catch (Exception e) {
                player.handleError(e);
            }
        }
        log.info("Player {} filled the position {}", player.getName(), latestPosition);
    }

    /**
     * Returns a player who won the game.
     * If draw, returns an empty optional
     * @return
     * @throws Exception
     */
    public Optional<Player> conductGame() throws Exception {
        while (true) {
            handlePlayerMove(player1, engineGameState, MoveSymbol.CROSS);
            if (GameEngineutil.isGameFinished(MoveSymbol.CROSS, engineGameState)) {
                return Optional.of(player1);
            };
            if (GameEngineutil.isDraw(engineGameState)) {
                return Optional.empty();
            }

            handlePlayerMove(player2, engineGameState, MoveSymbol.CIRCLE);
            if (GameEngineutil.isGameFinished(MoveSymbol.CIRCLE, engineGameState)) {
                return Optional.of(player2);
            };
            if (GameEngineutil.isDraw(engineGameState)) {
                Optional.empty();
            }
        }
    }

}
