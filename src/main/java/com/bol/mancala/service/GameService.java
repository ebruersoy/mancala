package com.bol.mancala.service;

import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.exception.PreconditionFailedException;
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
        if(game.isEnded()){
            throw new PreconditionFailedException("Game is over. Please restart to play again." , game);
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
