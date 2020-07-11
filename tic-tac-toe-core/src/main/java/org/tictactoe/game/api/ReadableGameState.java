package org.tictactoe.game.api;

import org.tictactoe.exceptions.IllegalPositionException;

/**
 * @author bjenuhb
 */

public interface ReadableGameState {

    void displayState();

    MoveSymbol getSymbolAtPosition(int position) throws IllegalPositionException;

    EngineGameState cloneGameState() throws Exception;

    int getSize();

}
