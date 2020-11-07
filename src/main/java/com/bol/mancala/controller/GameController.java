package com.bol.mancala.controller;

import com.bol.mancala.dto.ExceptionMessageResponse;
import com.bol.mancala.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ebru Ersoy Göksal
 */
@Controller
public class GameController {

    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value = "/sow")
    public String sow(ModelMap map, @RequestParam("pitIndex") Integer pitIndex){
        gameService.play(pitIndex);
        map.put("gameResponse", gameService.getGame());
        map.put("exceptionResponse", new ExceptionMessageResponse(""));
        return "index";
    }


    @GetMapping
    String getGame(ModelMap map) {
        map.put("gameResponse", gameService.getGame());
        map.put("exceptionResponse", new ExceptionMessageResponse(""));
        return "index";
    }

    @GetMapping(path = "restart")
    String restartGame(ModelMap map) {
        gameService.restartGame();
        map.put("gameResponse", gameService.getGame());
        map.put("exceptionResponse", new ExceptionMessageResponse(""));
        return "index";
    }


}
