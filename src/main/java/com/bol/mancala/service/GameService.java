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

    public GameService(Game game) {
        this.game = game;
    }

    public void play(Integer pitIndex) {
        if(pitIndex == null){
            throw new InvalidRequestException(getGame(), "Pit Index", pitIndex, "not empty");
        }
        game.play(pitIndex);
    }

    public Game getGame() {
        return game;
    }

    public void restartGame() {
        game = new Game();
    }
}
