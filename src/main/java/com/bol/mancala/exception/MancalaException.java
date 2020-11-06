package com.bol.mancala.exception;

import com.bol.mancala.dto.ExceptionMessageResponse;
import com.bol.mancala.model.Game;

/**
 * @author Ebru Ersoy Göksal
 */
public class MancalaException extends RuntimeException{
    private Game game;
    private ExceptionMessageResponse exceptionMessageResponse;

    public MancalaException(String message, Game game) {
        super(message);
        this.game = game;
        this.exceptionMessageResponse = new ExceptionMessageResponse(message);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ExceptionMessageResponse getExceptionMessageResponse() {
        return exceptionMessageResponse;
    }

    public void setExceptionMessageResponse(ExceptionMessageResponse exceptionMessageResponse) {
        this.exceptionMessageResponse = exceptionMessageResponse;
    }
}
