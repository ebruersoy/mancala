package com.bol.mancala.dto;

/**
 * @author Ebru Ersoy Göksal
 */

public class ExceptionMessageResponse {
    private String message = "";

    public ExceptionMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
