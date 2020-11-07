package com.bol.mancala.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ebru Ersoy Göksal
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(MancalaException.class)
    public final ResponseEntity<String> handleAllExceptions(MancalaException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(PreconditionFailedException.class)
    public final ModelAndView handleUnprocessableException(PreconditionFailedException ex, WebRequest request) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("gameResponse", ex.getGame());
        mav.addObject("exceptionResponse", ex.getExceptionMessageResponse());
        return mav;
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ModelAndView handleNoContentException(InvalidRequestException ex, WebRequest request) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("gameResponse", ex.getGame());
        mav.addObject("exceptionResponse", ex.getExceptionMessageResponse());
        return mav;
    }
}
