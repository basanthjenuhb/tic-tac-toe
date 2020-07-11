package org.tictactoe;

import org.tictactoe.game.api.GameManager;
import org.tictactoe.player.impl.ComputerPlayer;
import org.tictactoe.player.impl.HumanPlayer;

public class App {
    public static void main( String[] args ) {
        GameManager gameManager = new GameManager(new HumanPlayer(), new ComputerPlayer());
        gameManager.playgame();
    }
}
