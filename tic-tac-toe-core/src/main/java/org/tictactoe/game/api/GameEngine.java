package org.tictactoe.game.api;

import org.tictactoe.game.impl.GameStateImpl;
import org.tictactoe.player.api.Player;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bjenuhb
 */

@Slf4j
public class GameEngine {

    private Player player1, player2;
    private EngineGameState engineGameState;

    public GameEngine(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.engineGameState = new GameStateImpl(3);
    }

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

    public Player playGame() throws Exception {
        while (true) {

            handlePlayerMove(player1, engineGameState, MoveSymbol.CROSS);
            if (GameEngineutil.isGameFinished(MoveSymbol.CROSS, engineGameState)) {
                engineGameState.displayState();
                log.info("Player {} won the game.", player1.getName());
                return player1;
            };
            if (GameEngineutil.isDraw(engineGameState)) {
                engineGameState.displayState();
                log.info("Its a draw");
                return null;
            }

            handlePlayerMove(player2, engineGameState, MoveSymbol.CIRCLE);
            if (GameEngineutil.isGameFinished(MoveSymbol.CIRCLE, engineGameState)) {
                engineGameState.displayState();
                log.info("Player {} won the game.", player2.getName());
                return player2;
            };
            if (GameEngineutil.isDraw(engineGameState)) {
                engineGameState.displayState();
                log.info("Its a draw");
                return null;
            }

        }
    }

}
