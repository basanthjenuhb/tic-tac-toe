package org.tictactoe.game.api;

/**
 * @author bjenuhb
 */

public class GameEngineutil {

    public static boolean isGameFinished(MoveSymbol moveSymbol, ReadableGameState gameState) throws Exception {
        int size = gameState.getSize();
        // Check horizontal
        for (int i = 1; i <= size; i++) {
            boolean result = true;
            for (int j = 1; j <= size; j++) {
                int position = (i - 1) * size + j;
                if (gameState.getSymbolAtPosition(position) != moveSymbol) {
                    result = false;
                    break;
                }
            }
            if (result) {
                return true;
            }
        }

        // Check vertical
        for (int i = 1; i <= size; i++) {
            boolean result = true;
            for (int j = 1; j <= size; j++) {
                int position = i + (j - 1) * size;
                if (gameState.getSymbolAtPosition(position) != moveSymbol) {
                    result = false;
                    break;
                }
            }
            if (result) {
                return true;
            }
        }

        // Check diagonal
        boolean result = true;
        for (int i = 0; i < size; i++) {
            int position = i * size + i + 1;
            if (gameState.getSymbolAtPosition(position) != moveSymbol) {
                result = false;
                break;
            }
        }
        if (result) {
            return true;
        }

        // Check reverse diagonal
        result = true;
        for (int i = 0; i < size; i++) {
            int position = size * (i + 1) - i;
            if (gameState.getSymbolAtPosition(position) != moveSymbol) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isDraw(ReadableGameState gameState) throws Exception {
        int size = gameState.getSize();
        for (int i = 1; i <= size * size; i++) {
            if (gameState.getSymbolAtPosition(i) == MoveSymbol.EMPTY) {
                return false;
            }
        }
        return true;
    }

}
