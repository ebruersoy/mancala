package com.bol.mancala.service;

import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.model.Board;
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
        if(pitIndex < 0 || pitIndex > Board.totalPits - 1){
            throw new InvalidRequestException(getGame(), "Pit Index", pitIndex, "in range [0-13]");
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
