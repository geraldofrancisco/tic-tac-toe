package com.dti.tictactoe.dtos;

import com.dti.tictactoe.enums.Result;

public class EndGameDTO {
    private String msg;
    private Result winner;

    public EndGameDTO(String msg, Result winner) {
        this.msg = msg;
        this.winner = winner;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getWinner() {
        return winner;
    }

    public void setWinner(Result winner) {
        this.winner = winner;
    }
}
