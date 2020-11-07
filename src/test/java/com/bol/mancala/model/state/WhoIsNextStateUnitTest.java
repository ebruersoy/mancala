package com.bol.mancala.model.state;

import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.util.PlayerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class WhoIsNextStateUnitTest {

    Game game;
    WhoIsNextState whoIsNextState;

    @BeforeEach
    void setUp() {
        whoIsNextState = new WhoIsNextState();
        game = new Game();
        game.setCurrentState(whoIsNextState);
    }

    @Test
    void next_state_should_be_continue_game_state() {
        game.getBoard().setLastPit(game.getBoard().getPits()[3]);
        whoIsNextState.execute(game, anyInt());
        assertTrue(game.getCurrentState() instanceof ContinueGameState);
    }

    @Test
    void next_state_should_be_capture_stones_state() {
        game.setCurrentPlayer(game.getPlayer1());
        game.getBoard().setLastPit(game.getBoard().getPits()[3]);
        game.getBoard().getLastPit().setStoneCount(1);
        whoIsNextState.execute(game, anyInt());
        assertTrue(game.getCurrentState() instanceof CaptureStoneState);
        assertEquals(game.getNextPlayerType(), PlayerType.OPPONENT);
    }

    @Test
    void next_turn_should_be_same_player_when_last_pit_is_own_house(){
        game.setCurrentPlayer(game.getPlayer1());
        game.getBoard().setLastPit(game.getBoard().getPits()[Board.houseIndexOfPlayer1]);
        whoIsNextState.execute(game, anyInt());
        assertEquals(game.getNextPlayerType(), PlayerType.CURRENT);
        assertTrue(game.getCurrentState() instanceof ContinueGameState);
    }

    @Test
    void next_player_type_should_be_opponent() {
        game.getBoard().setLastPit(game.getBoard().getPits()[3]);
        whoIsNextState.execute(game, anyInt());
        assertEquals(game.getNextPlayerType() , PlayerType.OPPONENT);
    }
}