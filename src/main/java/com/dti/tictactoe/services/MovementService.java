package com.dti.tictactoe.services;

import com.dti.tictactoe.enums.Player;
import com.dti.tictactoe.models.Movement;

public interface MovementService {
    Movement getByGameHashAndPositions(String hash, Integer x, Integer y);

    Movement saveMoviment(Movement entity);

    boolean checkByPositionsAndGameIdAndGamePlayer
            (Long gameId, Player player, Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3);

    boolean checkDrawGame(Long gameId);
}
