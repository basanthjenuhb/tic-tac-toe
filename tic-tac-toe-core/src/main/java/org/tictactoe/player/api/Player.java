package org.tictactoe.player.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tictactoe.game.api.MoveSymbol;
import org.tictactoe.game.api.ReadableGameState;

/**
 * @author bjenuhb
 */

public interface Player {

    static Logger log = LoggerFactory.getLogger(Player.class);

    void assignSymbol(MoveSymbol moveSymbol);

    String getName();

    int makeMove(ReadableGameState gameState);

    default void handleError(Exception e) {
        log.info("Error occured: {}", e.getMessage());
    }

}
