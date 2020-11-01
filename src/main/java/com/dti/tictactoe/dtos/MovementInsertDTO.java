package com.dti.tictactoe.dtos;

import com.dti.tictactoe.enums.Player;

public class MovementInsertDTO {
    private String id;

    private String player;

    private PositionDTO position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer() {
        return player.toUpperCase();
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public Player getPlayerEnum() {
        return Player.valueOf(this.getPlayer());
    }
}
