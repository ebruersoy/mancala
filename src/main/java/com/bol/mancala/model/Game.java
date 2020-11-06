package com.bol.mancala.model;

import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.state.EndGameState;
import com.bol.mancala.model.state.IState;
import com.bol.mancala.model.state.WaitState;
import com.bol.mancala.util.Constants;
import com.bol.mancala.util.PlayerType;

/**
 * @author Ebru Ersoy Göksal
 */

public class Game {
    private IState currentState;
    private final Board board;
    private Player currentPlayer;
    private PlayerType nextPlayerType;
    private Player player1;
    private Player player2;
    private String winner;
    private boolean ended = false;

    public Game() {
        createPlayers();
        currentPlayer = player1;
        nextPlayerType = PlayerType.OPPONENT;
        board = new Board(player1, player2);
        currentState = new WaitState();
    }

    private void createPlayers() {
        player1 = new Player(Constants.startIndexOfPlayer1, "Player 1");
        player2 = new Player(Constants.startIndexOfPlayer2, "Player 2");
    }

    public void play(int selectedPitIndex){
        if(currentState instanceof EndGameState){
            throw new PreconditionFailedException("Game is over. Please restart to play again." , this);
        }
        currentState.handle(this,selectedPitIndex);
    }

    public Player getOpponent() {
        if(currentPlayer.getId() == player1.getId()){
            return player2;
        }
        return player1;
    }

    public IState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    public Board getBoard() {
        return board;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public PlayerType getNextPlayerType() {
        return nextPlayerType;
    }

    public void setNextPlayerType(PlayerType nextPlayerType) {
        this.nextPlayerType = nextPlayerType;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}