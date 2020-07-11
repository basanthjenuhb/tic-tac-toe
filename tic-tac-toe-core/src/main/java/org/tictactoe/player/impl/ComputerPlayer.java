package org.tictactoe.player.impl;

import org.tictactoe.game.api.EngineGameState;
import org.tictactoe.game.api.GameEngineutil;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.game.api.ReadableGameState;
import org.tictactoe.player.api.Player;

/**
 * @author bjenuhb
 */

public class ComputerPlayer implements Player {

    private MoveSymbol moveSymbol;
    private MoveSymbol otherPlayerSymbol;
    private int movesMade = -1;

    @Override
    public void assignSymbol(MoveSymbol moveSymbol) {
        this.moveSymbol = moveSymbol;
        if (this.moveSymbol == MoveSymbol.CROSS) {
            otherPlayerSymbol = MoveSymbol.CIRCLE;
        } else {
            otherPlayerSymbol = MoveSymbol.CROSS;
        }
    }

    @Override
    public String getName() {
        return "Computer";
    }

    @Override
    public void handleError(Exception e) {
        log.info("Error occured: {}", e.getMessage());
        movesMade--;
    }

    @Override
    public int makeMove(ReadableGameState gameState) {
        int result = 0;
        try {
            int maxCount = Integer.MIN_VALUE;
            for (int i = 1; i <= 9; i++) {
                if (gameState.getSymbolAtPosition(i) == MoveSymbol.EMPTY) {
                    EngineGameState engineGameState = gameState.cloneGameState();
                    engineGameState.setSymbolAtPosition(i, this.moveSymbol);
                    int bestMove = getBestMove(engineGameState, false, 0);
                    if (bestMove > maxCount) {
                        maxCount = bestMove;
                        result = i;
                    }
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    private int getBestMove(EngineGameState engineGameState, boolean isMaximizer, int depth) throws Exception {

        if (isMaximizer && GameEngineutil.isGameFinished(this.otherPlayerSymbol, engineGameState)) {
            if (depth == 1) {
                return Integer.MIN_VALUE;
            }
            return -1;
        } else if (GameEngineutil.isGameFinished(this.moveSymbol, engineGameState)) {
            if (depth == 0) {
                return Integer.MAX_VALUE;
            }
            return 1;
        } else if (GameEngineutil.isDraw(engineGameState)) {
            return 0;
        }

        int movePoints = 0, size = engineGameState.getSize();
        for (int i = 1; i <= size * size; i++) {
            if (engineGameState.getSymbolAtPosition(i) == MoveSymbol.EMPTY) {
                MoveSymbol currentMoveSymbol;
                if (isMaximizer) {
                    currentMoveSymbol = this.moveSymbol;
                } else {
                    currentMoveSymbol = this.otherPlayerSymbol;
                }
                EngineGameState newGameState = engineGameState.cloneGameState();
                newGameState.setSymbolAtPosition(i, currentMoveSymbol);
                int bestMove = getBestMove(newGameState, !isMaximizer, depth + 1);
                // To avoid integer overflow
                if (bestMove == Integer.MIN_VALUE || bestMove == Integer.MAX_VALUE) {
                    return bestMove;
                } else {
                    movePoints += bestMove;
                }
            }
        }
        return movePoints;
    }
}
