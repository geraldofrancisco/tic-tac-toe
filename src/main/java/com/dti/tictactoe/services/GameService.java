package com.dti.tictactoe.services;

import com.dti.tictactoe.dtos.EndGameDTO;
import com.dti.tictactoe.dtos.GameDTO;
import com.dti.tictactoe.dtos.MovementInsertDTO;

public interface GameService {
    GameDTO createGame();
    EndGameDTO makePlay(MovementInsertDTO dto);
}
