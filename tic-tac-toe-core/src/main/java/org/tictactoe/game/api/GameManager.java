package org.tictactoe.game.api;

import org.tictactoe.player.api.Player;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bjenuhb
 */

@Slf4j
public class GameManager {

    private GameEngine gameEngine;
    private Player player1;
    private Player player2;

    public GameManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        init();
        this.gameEngine = new GameEngine(this.player1, this.player2);
    }

    private void init() {
        log.info("Conducting toss between 2 players");
        double random = Math.random();
        if (random > 0.5) {
            Player temp = this.player1;
            this.player1 = this.player2;
            this.player2 = temp;
        }
        player1.assignSymbol(MoveSymbol.CROSS);
        player2.assignSymbol(MoveSymbol.CIRCLE);

        log.info("First player is {} - ({})", player1.getName(), MoveSymbol.CROSS.getSymbol());
        log.info("Second player is {} - ({})", player2.getName(), MoveSymbol.CIRCLE.getSymbol());
    }

    public void playgame() {
        try {
            Optional<Player> player = this.gameEngine.playGame();
            this.gameEngine.displayGameState();
            player.ifPresentOrElse(p -> {
                log.info("Player {} won the game. Congratulations \uD83D\uDE00", p.getName());
            }, () -> {
                log.info("It is a draw \uD83D\uDE00");
            });
        } catch (Exception e) {
            log.error("Error while playing game");
        }
    }
}
