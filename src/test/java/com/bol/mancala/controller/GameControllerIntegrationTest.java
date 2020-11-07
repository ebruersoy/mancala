package com.bol.mancala.controller;

import com.bol.mancala.exception.CustomExceptionHandler;
import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("IntegrationTest")
@AutoConfigureMockMvc
@ContextConfiguration(classes = {GameController.class, GameService.class, Game.class})
@WebMvcTest
public class GameControllerIntegrationTest {

    @Autowired
    GameController gameController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    public void should_restart_game(){
        String sowResponse = gameController.restartGame(new ModelMap());
        assertEquals(sowResponse, "index");
    }

    @Test
    public void should_sow(){
        String sowResponse = gameController.sow(new ModelMap(), 1);
        assertEquals(sowResponse, "index");
    }

    @Test
    public void should_get_game(){
        String response = gameController.getGame(new ModelMap());
        assertEquals(response, "index");
    }

    @Test
    public void should_throws_exception_when_select_house_index() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sow")
                .param("pitIndex", String.valueOf(Board.houseIndexOfPlayer1))).andReturn();

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertTrue(result.getResolvedException() instanceof PreconditionFailedException);
        PreconditionFailedException preconditionFailedException = (PreconditionFailedException) result.getResolvedException();
        assertEquals(preconditionFailedException.getMessage(),"House Pits can not be selected!");
    }
    @Test
    public void should_throws_exception_when_select_empty_index() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/sow")
                .param("pitIndex", "")).andReturn();

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertTrue(result.getResolvedException() instanceof InvalidRequestException);
        InvalidRequestException invalidRequestException = (InvalidRequestException) result.getResolvedException();
        assertEquals(invalidRequestException.getField(),"Pit Index");
        assertEquals(invalidRequestException.getExpected(),"not empty");
    }

    @Test
    public void should_play() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andReturn();
        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(result.getModelAndView().getViewName(),"index");
        Object gameResponse = result.getModelAndView().getModel().get("gameResponse");
        assertNotNull(gameResponse);
    }
    @Test
    public void should_restart() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/restart")).andReturn();
        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(result.getModelAndView().getViewName(),"index");
        Object gameResponse = result.getModelAndView().getModel().get("gameResponse");
        assertNotNull(gameResponse);
    }
}