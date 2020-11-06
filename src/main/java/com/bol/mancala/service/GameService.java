package com.bol.mancala.service;

import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.model.Game;
import org.springframework.stereotype.Component;

/**
 * @author Ebru Ersoy Göksal
 */
@Component
public class GameService {
    private Game game;

    public GameService() {
        this.game = new Game();
    }

    public void play(Integer pitId) {
        if(pitId == null){
            throw new InvalidRequestException(getGame(), "Pit Id", pitId, "non empty");
        }
        game.play(pitId);
    }

    public Game getGame() {
        return game;
    }

    public void restartGame() {
        game = new Game();
    }
}
