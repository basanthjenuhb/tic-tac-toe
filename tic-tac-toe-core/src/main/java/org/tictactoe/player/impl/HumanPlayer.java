package org.tictactoe.player.impl;

import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.game.api.ReadableGameState;
import org.tictactoe.player.api.Player;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bjenuhb
 */

@Slf4j
public class HumanPlayer implements Player {

    private Scanner scanner;
    private MoveSymbol moveSymbol;
    private String name;

    public HumanPlayer() {
        this.scanner = new Scanner(System.in);
        log.info("Hello human player, Please enter your name: ");
        name = scanner.next();
    }

    @Override
    public void assignSymbol(MoveSymbol moveSymbol) {
        this.moveSymbol = moveSymbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int makeMove(ReadableGameState gameState) {
        log.info("Please enter a position, Your symbol is {}", this.moveSymbol.getSymbol());
        int nextPosition = scanner.nextInt();
        return nextPosition;
    }
}
