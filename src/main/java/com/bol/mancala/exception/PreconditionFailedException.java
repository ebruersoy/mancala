package com.bol.mancala.exception;

import com.bol.mancala.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ebru Ersoy Göksal
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PreconditionFailedException extends MancalaException {

    public PreconditionFailedException(String reason, Game game) {
        super(reason, game);
    }

}
