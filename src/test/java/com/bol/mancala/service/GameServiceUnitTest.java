package com.bol.mancala.service;

import com.bol.mancala.exception.InvalidRequestException;
import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;
import com.bol.mancala.model.Player;
import com.bol.mancala.model.state.EndGameState;
import com.bol.mancala.model.state.WaitState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class GameServiceUnitTest {
    GameService gameService;

    @BeforeEach
    void setUp(){
        gameService = new GameService(new Game());
    }

    @AfterEach
    void tearDown() {
        gameService.restartGame();
    }

    @Test
    void play_should_throws_exception_with_null_pit_id() {
        InvalidRequestException invalidRequestException = assertThrows(InvalidRequestException.class, () -> {
            gameService.play(null);
        });
        assertTrue(invalidRequestException.getExceptionMessageResponse().getMessage().contains("Invalid value for field Pit Index"));
    }
    @Test
    void play_should_throws_exception_in_endgame_state() {
        PreconditionFailedException preconditionFailedException = assertThrows(PreconditionFailedException.class, () -> {
            gameService.getGame().setCurrentState(new EndGameState());
            gameService.play(1);
        });
        assertEquals(preconditionFailedException.getExceptionMessageResponse().getMessage(), "Game is over. Please restart to play again.");
    }

    @Test
    void should_get_not_null_game() {
        Game game = gameService.getGame();
        assertNotNull(game);
    }

    @Test
    void should_game_in_initial_state() {
        Game game = gameService.getGame();
        assertTrue(game.getCurrentState() instanceof WaitState);
        assertFalse(game.isEnded());
        assertNull(game.getWinner());
    }

    @Test
    void should_players_in_initial_state(){
        Game game = gameService.getGame();
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertEquals(game.getPlayer1().getHouseIndex(), Board.houseIndexOfPlayer1);
        assertEquals(game.getPlayer1().getStartIndex(), Board.startIndexOfPlayer1);
        assertEquals(game.getPlayer1().getEndIndex(), Board.startIndexOfPlayer1 + Player.pitCountForEachPlayer - 1);
        assertEquals(game.getPlayer2().getHouseIndex(), Board.houseIndexOfPlayer2);
        assertEquals(game.getPlayer2().getStartIndex(), Board.startIndexOfPlayer2);
        assertEquals(game.getPlayer2().getEndIndex(), Board.startIndexOfPlayer2 + Player.pitCountForEachPlayer - 1);
    }

    @Test
    void should_board_in_initial_state(){
        Game game = gameService.getGame();
        Board board = game.getBoard();
        assertNotNull(board);
        assertNotNull(board.getPits());
        assertEquals(board.getPits().length, Board.totalPits);
    }

    @Test
    void should_pits_in_initial_state(){
        Board board =  gameService.getGame().getBoard();
        assertNull(board.getLastPit());
        Pit[] pits = board.getPits();
        assertTrue(pits[Board.houseIndexOfPlayer1].isHouse());
        assertTrue(pits[Board.houseIndexOfPlayer2].isHouse());
        for(int i = 0; i<Board.totalPits; i++){
            if(pits[i].isHouse()){
                assertEquals(pits[i].getStoneCount(), 0);
            }else{
                assertEquals(pits[i].getStoneCount(), Board.initialStoneCountForEachPit);
            }
        }
        for(int i = Board.startIndexOfPlayer1; i < Board.houseIndexOfPlayer1; i++){
            assertEquals(pits[i].getOwner().getId(), Board.startIndexOfPlayer1);
        }
        for(int i = Board.startIndexOfPlayer2; i < Board.houseIndexOfPlayer2; i++){
            assertEquals(pits[i].getOwner().getId(), Board.startIndexOfPlayer2);
        }
    }

    @Test
    public void should_restart_game_in_wait_state(){
        gameService.play(1);
        gameService.restartGame();
        assertTrue(gameService.getGame().getCurrentState() instanceof WaitState);
    }
}