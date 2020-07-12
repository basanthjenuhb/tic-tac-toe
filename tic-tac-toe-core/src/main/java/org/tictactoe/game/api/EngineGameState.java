package org.tictactoe.game.api;

import org.tictactoe.exceptions.IllegalMoveException;
import org.tictactoe.exceptions.IllegalPositionException;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.game.api.ReadableGameState;

/**
 * @author bjenuhb
 */

public interface EngineGameState extends ReadableGameState {

    void setSymbolAtPosition(int position, MoveSymbol symbol) throws IllegalPositionException, IllegalMoveException;

}
