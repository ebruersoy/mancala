package com.bol.mancala.exception;

import com.bol.mancala.model.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ebru Göksal
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends MancalaException {

    private String field;
    private String expected;

    public InvalidRequestException(Game game, String field, Object value, Object expected) {
        super("Invalid value for field " + field + " : { " + (value == null ? " [ null ] " : value.toString()) + " }. Should be { " + expected.toString() + " }"
        , game);
        this.field = field;
        this.expected = expected.toString();
    }

    public String getField() {
        return field;
    }

    public String getExpected() {
        return expected;
    }

}

