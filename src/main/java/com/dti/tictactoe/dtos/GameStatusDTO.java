package com.dti.tictactoe.dtos;

import com.dti.tictactoe.enums.Player;
import com.dti.tictactoe.enums.Status;

public class GameStatusDTO {
    private Player nextPlayer;
    private Player winner;
    private Status status;

    public GameStatusDTO(Player nextPlayer, Status status) {
        this.nextPlayer = nextPlayer;
        this.status = status;
    }

    public boolean isGameOver() {
        return this.status.equals(Status.DRAW) || this.status.equals(Status.WINNER);
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
