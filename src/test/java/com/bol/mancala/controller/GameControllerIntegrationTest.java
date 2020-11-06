package com.bol.mancala.controller;

import com.bol.mancala.IntegrationTest;
import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.model.state.WaitState;
import com.bol.mancala.util.Constants;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ModelMap;

import static org.junit.Assert.assertTrue;


/**
 * @author Ebru Ersoy Göksal
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameControllerIntegrationTest {

    @Autowired
    GameController gameController;

    @Test(expected = InvalidRequestException.class)
    public void should_throw_exception_sow_with_null_pit_id() {
        gameController.sow(null, null);
    }

    @Test
    public void should_get_game_in_wait_state(){
        gameController.getGame(new ModelMap());
        assertTrue(gameController.gameService.getGame().getCurrentState() instanceof WaitState);
    }

    @Test
    public void should_sow(){
        gameController.sow(new ModelMap(), 1);
        assertTrue(gameController.gameService.getGame().getBoard().getPits()[Constants.houseIndexOfPlayer1].getStoneCount() == 1);
    }
}