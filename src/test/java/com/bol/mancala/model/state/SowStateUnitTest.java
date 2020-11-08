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
class SowStateUnitTest {

    Game game;
    SowState sowState;

    @BeforeEach
    void setUp() {
        sowState = new SowState();
        game = new Game();
    }

    @Test
    void next_state_should_be_who_is_next_state() {
        sowState.execute(game, 1);
        assertTrue(game.getCurrentState() instanceof WhoIsNextState);
    }

    @Test
    void last_pit_after_sowing_should_be_house_of_player_1(){
        game.setCurrentPlayer(game.getPlayer1());
        int previousIndexOfHouseOfPlayer1 = Board.houseIndexOfPlayer1 - 1;
        game.getBoard().getPits()[previousIndexOfHouseOfPlayer1].setStoneCount(1);
        game.getBoard().getPits()[Board.houseIndexOfPlayer1].setStoneCount(0);
        sowState.execute(game, previousIndexOfHouseOfPlayer1);
        assertEquals(game.getBoard().getLastPit().getIndex(), Board.houseIndexOfPlayer1);
        assertEquals(game.getBoard().getLastPit().getStoneCount(), 1);
    }

    @Test
    void opponents_house_should_not_be_sowed(){
        game.setCurrentPlayer(game.getPlayer1());
        int selectedPitIndex = Board.startIndexOfPlayer1;
        game.getBoard().getPits()[selectedPitIndex].setStoneCount(Board.totalPits);
        game.getBoard().getPits()[Board.houseIndexOfPlayer2].setStoneCount(0);
        sowState.execute(game, selectedPitIndex);
        assertEquals(game.getBoard().getPits()[Board.houseIndexOfPlayer2].getStoneCount(), 0);
    }

    @Test
    void more_stones_should_be_sowed_after_total_pits(){
        game.setCurrentPlayer(game.getPlayer1());
        int selectedPitIndex = Board.startIndexOfPlayer1;
        game.getBoard().getPits()[selectedPitIndex].setStoneCount(Board.totalPits);
        sowState.execute(game, selectedPitIndex);
        assertEquals(game.getBoard().getPits()[selectedPitIndex].getStoneCount(), 1);
    }

    @Test
    void pits_with_one_stone_should_be_passed_next_pit(){
        game.setCurrentPlayer(game.getPlayer1());
        int selectedPitIndex = Board.startIndexOfPlayer1;
        game.getBoard().getPits()[selectedPitIndex].setStoneCount(1);
        int nextPitIndex = game.getBoard().getPits()[selectedPitIndex].nextPitIndex();
        game.getBoard().getPits()[nextPitIndex].setStoneCount(5);
        sowState.execute(game, selectedPitIndex);
        assertEquals(game.getBoard().getPits()[selectedPitIndex].getStoneCount(), 0);
        assertEquals(game.getBoard().getPits()[nextPitIndex].getStoneCount(), 6);
    }

    @Test
    void should_sow_in_correct_count(){
        game.setCurrentPlayer(game.getPlayer1());
        int selectedPitIndex = Board.startIndexOfPlayer1;
        game.getBoard().getPits()[selectedPitIndex].setStoneCount(1);
        int nextPitIndex = game.getBoard().getPits()[selectedPitIndex].nextPitIndex();
        game.getBoard().getPits()[nextPitIndex].setStoneCount(5);
        int nextPitIndexOfNextPit = game.getBoard().getPits()[nextPitIndex].nextPitIndex();
        game.getBoard().getPits()[nextPitIndexOfNextPit].setStoneCount(3);
        sowState.execute(game, selectedPitIndex);
        assertEquals(game.getBoard().getPits()[selectedPitIndex].getStoneCount(), 0);
        assertEquals(game.getBoard().getPits()[nextPitIndex].getStoneCount(), 6);
        assertEquals(game.getBoard().getPits()[nextPitIndexOfNextPit].getStoneCount(), 3);
    }


}