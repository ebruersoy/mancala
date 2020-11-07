package com.bol.mancala.model.state;

import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class WaitStateUnitTest {

    Game game;
    WaitState waitState;

    @BeforeEach
    void setUp() {
        waitState = new WaitState();
        game = new Game();
    }

    @Test
    void next_state_should_be_sow_state() {
        waitState.execute(game, 1);
        assertTrue(game.getCurrentState() instanceof SowState);
    }

    @Test
    void expected_exception_empty_pits_cannot_be_selected() {
        PreconditionFailedException preconditionFailedException = assertThrows(PreconditionFailedException.class, () -> {
            game.getBoard().getPits()[1].setStoneCount(0);
            waitState.handle(game, 1);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "Empty Pits can not be selected!");
    }
    @Test
    void expected_exception_house_pits_cannot_be_selected() {
        PreconditionFailedException preconditionFailedException = assertThrows(PreconditionFailedException.class, () -> {
            waitState.handle(game, Board.houseIndexOfPlayer1);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "House Pits can not be selected!");

        preconditionFailedException = Assertions.assertThrows(PreconditionFailedException.class, () -> {
            waitState.handle(game, Board.houseIndexOfPlayer2);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "House Pits can not be selected!");
    }
    @Test
    void expected_exception_opponents_pits_cannot_be_selected() {
        PreconditionFailedException preconditionFailedException = assertThrows(PreconditionFailedException.class, () -> {
            game.setCurrentPlayer(game.getPlayer1());
            waitState.handle(game, Board.startIndexOfPlayer2);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "Players can not select opponent's pits!");
        preconditionFailedException = Assertions.assertThrows(PreconditionFailedException.class, () -> {
            game.setCurrentPlayer(game.getPlayer2());
            waitState.handle(game, Board.startIndexOfPlayer1);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "Players can not select opponent's pits!");
    }
}