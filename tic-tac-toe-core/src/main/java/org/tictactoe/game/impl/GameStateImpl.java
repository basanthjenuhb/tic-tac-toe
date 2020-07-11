package org.tictactoe.game.impl;

import org.tictactoe.exceptions.IllegalMoveException;
import org.tictactoe.exceptions.IllegalPositionException;
import org.tictactoe.game.api.EngineGameState;
import org.tictactoe.game.api.MoveSymbol;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bjenuhb
 * This Game State is a dumb state store for the game moves;
 * We store the symbols in a 1d array.
 * It has some additional logic to check for illegal moves;
 */

@Slf4j
public class GameStateImpl implements EngineGameState {

    private int size;
    private MoveSymbol[] gameBoard;

    public GameStateImpl(int size) {
        this.size = size;
        init();
    }

    /**
     * Initialize the game board with empty positions
     */
    private void init() {
        gameBoard = new MoveSymbol[size * size + 1];
        Arrays.fill(gameBoard, MoveSymbol.EMPTY);
    }

    /**
     * Checks if the position is within the board constraints
     * @param position
     * @throws IllegalPositionException
     */
    private void validatePosition(int position) throws IllegalPositionException {
        if (position <= 0 || position > size * size) {
            String errorMessage = String.format(
                "Illegal position: %d, the position should be between %d and %d",
                position, 1, size * size
            );
            throw new IllegalPositionException(errorMessage);
        }
    }

    /**
     * Checks if the position is legal and is empty for the player to make his move
     * @param position
     * @throws IllegalPositionException
     * @throws IllegalMoveException
     */
    private void validatePositionEmpty(int position) throws IllegalPositionException, IllegalMoveException {
        validatePosition(position);
        if (getSymbolAtPosition(position) != MoveSymbol.EMPTY) {
            String errorMessage = String.format("The position: %d is not empty.", position);
            throw new IllegalMoveException(errorMessage);
        }
    }

    /**
     * Thecks if the move symbol provided is valid.
     * @param moveSymbol
     * @throws IllegalMoveException
     */
    private void validateMove(MoveSymbol moveSymbol) throws IllegalMoveException {
        if (moveSymbol == null) {
            String errorMessage = String.format("Illegal moveSymbol: %s, moveSymbol should not be null",moveSymbol);
            throw new IllegalMoveException(errorMessage);
        }
    }

    /**
     * Gets the symbol at position
     * @param position
     * @return
     * @throws IllegalPositionException
     */
    public MoveSymbol getSymbolAtPosition(int position) throws IllegalPositionException {
        validatePosition(position);
        return this.gameBoard[position];
    }

    /**
     * Sets a symbol at a position
     * @param position
     * @param symbol
     * @throws IllegalPositionException
     * @throws IllegalMoveException
     */
    @Override
    public void setSymbolAtPosition(int position, MoveSymbol symbol) throws IllegalPositionException, IllegalMoveException {
        validatePositionEmpty(position);
        validateMove(symbol);
        this.gameBoard[position] = symbol;
    }

    @Override
    public void displayState() {
        String CELL_DELIMITER = "|";
        String SYMBOL_FORMATTER = "-";
        log.info("\n");
        for (int i = 1; i <= size; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 1; j <= size; j++) {
                int position = size * (i - 1) + j;
                row.append(CELL_DELIMITER);
                if (gameBoard[position] == MoveSymbol.EMPTY) {
                    row.append("  ")
                        .append(SYMBOL_FORMATTER)
                        .append(position)
                        .append(SYMBOL_FORMATTER)
                        .append("  ");
                } else {
                    row.append("   ")
                        .append(gameBoard[position].getSymbol())
                        .append("   ");
                }
            }
            row.append(CELL_DELIMITER);
            log.info(row.toString());
            log.info(SYMBOL_FORMATTER.repeat(row.length()));
        }
        log.info("\n");
    }

    @Override
    public EngineGameState cloneGameState() throws Exception {
        EngineGameState engineGameState = new GameStateImpl(size);
        for (int i = 1; i <= size * size; i++) {
            engineGameState.setSymbolAtPosition(i, this.getSymbolAtPosition(i));
        }
        return engineGameState;
    }

    @Override
    public int getSize() {
        return size;
    }
}
