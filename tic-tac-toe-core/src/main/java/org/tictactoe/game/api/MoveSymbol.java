package org.tictactoe.game.api;

/**
 * @author bjenuhb
 */

public enum MoveSymbol {
    CROSS('X'),
    CIRCLE('O'),
    EMPTY('-');

    private char symbol;

    MoveSymbol() {
    }

    MoveSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return "symbol = " + symbol;
    }
}
