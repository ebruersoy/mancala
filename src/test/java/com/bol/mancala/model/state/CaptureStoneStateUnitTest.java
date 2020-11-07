package com.bol.mancala.model.state;

import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class CaptureStoneStateUnitTest {

    Game game;
    CaptureStoneState captureStoneState;

    @BeforeEach
    void setUp() {
        captureStoneState = new CaptureStoneState();
        game = new Game();
        game.setCurrentState(captureStoneState);
    }

    @Test
    void should_capture_stones(){
        game.setCurrentPlayer(game.getPlayer1());
        game.getBoard().getPits()[Board.houseIndexOfPlayer1].setStoneCount(5);
        game.getBoard().setLastPit(game.getBoard().getPits()[3]);
        game.getBoard().getLastPit().setStoneCount(1);
        int opponentPitIndexForCapture = game.getBoard().getPits()[3].getOpponentPitIndexForCapture();
        game.getBoard().getPits()[opponentPitIndexForCapture].setStoneCount(10);
        captureStoneState.execute(game, 9);
        assertEquals(game.getBoard().getPits()[Board.houseIndexOfPlayer1].getStoneCount(), 16);
        assertTrue(game.getCurrentState() instanceof ContinueGameState);
    }
}