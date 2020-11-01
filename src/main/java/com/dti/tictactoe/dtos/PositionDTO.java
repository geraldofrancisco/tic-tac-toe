package com.dti.tictactoe.dtos;

import javax.validation.constraints.NotNull;

public class PositionDTO {

    private Integer x;

    private Integer y;

    public PositionDTO() {
        //not implemented
    }

    public PositionDTO(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isEmptyCoordinate() {
        return this.x == null || this.y == null;
    }
}
