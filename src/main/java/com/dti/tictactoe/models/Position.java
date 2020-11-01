package com.dti.tictactoe.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Position implements Serializable {

    @Column(name = "position_x")
    private Integer x;

    @Column(name = "position_Y")
    private Integer y;

    public Position() {
        //not implemented
    }

    public Position(Integer x, Integer y) {
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
}
