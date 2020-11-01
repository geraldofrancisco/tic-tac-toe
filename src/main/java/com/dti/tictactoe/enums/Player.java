package com.dti.tictactoe.enums;

import java.util.Arrays;

public enum Player {
    X, O;

    public static boolean checkIfValidString(String value) {
        if(value == null)
            return false;

        return Arrays.stream(Player.values())
                .anyMatch(v -> v.name().equals(value));
    }
}
