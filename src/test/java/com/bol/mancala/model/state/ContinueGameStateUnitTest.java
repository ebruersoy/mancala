package com.bol.mancala.model.state;

import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class ContinueGameStateUnitTest {

    Game game;
    ContinueGameState continueGameState;

    @BeforeEach
    void setUp() {
        continueGameState = new ContinueGameState();
        game = new Game();
        game.setCurrentState(continueGameState);
    }

    @Test
    void next_state_should_be_end_game_state(){
        Pit[] pits = game.getBoard().getPits();
        for (int i = Board.startIndexOfPlayer1; i< Board.houseIndexOfPlayer1; i++){
            pits[i].setStoneCount(0);
        }
        continueGameState.execute(game,anyInt());
        assertTrue(game.getCurrentState() instanceof EndGameState);
    }
    @Test
    void next_state_should_be_wait_state(){
        Pit[] pits = game.getBoard().getPits();
        pits[Board.startIndexOfPlayer1].setStoneCount(3);
        pits[Board.startIndexOfPlayer2].setStoneCount(5);
        continueGameState.execute(game, anyInt());
        assertTrue(game.getCurrentState() instanceof WaitState);
    }
}