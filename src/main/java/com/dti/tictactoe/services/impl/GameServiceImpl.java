package com.dti.tictactoe.services.impl;

import com.dti.tictactoe.dtos.EndGameDTO;
import com.dti.tictactoe.dtos.GameDTO;
import com.dti.tictactoe.dtos.GameStatusDTO;
import com.dti.tictactoe.dtos.MovementInsertDTO;
import com.dti.tictactoe.enums.Player;
import com.dti.tictactoe.enums.Result;
import com.dti.tictactoe.enums.Status;
import com.dti.tictactoe.exceptions.UnhealthyDataException;
import com.dti.tictactoe.mapper.GameMapper;
import com.dti.tictactoe.models.Game;
import com.dti.tictactoe.models.Movement;
import com.dti.tictactoe.models.Position;
import com.dti.tictactoe.repository.GameRepository;
import com.dti.tictactoe.services.GameService;
import com.dti.tictactoe.services.MovementService;
import com.dti.tictactoe.utils.TicTacToeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private static final String INVALID_COORDINATE = "Coordenadas inválidas";
    private static final String INVALID_PLAYER = "Jogador inválido";
    private static final String MATCH_NOT_FOUND = "Partida não encontrada";
    private static final String MATCH_ENDED = "Partida finalizada";
    private static final String IT_IS_NOT_PLAYER_TURN = "Não é turno do jogador";
    private static final String MOVEMENT_ALREADY_PERFORMED = "Movimento já realizado anteriormente por algum jogador";

    private final GameRepository repository;
    private final TicTacToeUtils utils;
    private final GameMapper mapper;
    private final MovementService movementService;

    public GameServiceImpl(GameRepository repository, TicTacToeUtils utils, GameMapper mapper, MovementService movementService) {
        this.repository = repository;
        this.utils = utils;
        this.mapper = mapper;
        this.movementService = movementService;
    }

    @Override
    public GameDTO createGame() {
        Game game = new Game();
        game.setNextPlayer(this.utils.chooseRadomPlayer());
        game.setStatus(Status.PLAYING);
        game.setHash(this.utils.nowMd5());
        this.createMovements(game);
        return this.mapper.toDto(repository.save(game));
    }

    private void createMovements(Game game) {
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                game.getMoviments().add(new Movement(game, new Position(x, y)));
            }
        }
    }

    @Override
    public EndGameDTO makePlay(MovementInsertDTO dto) {
        Movement movement = this.validate(dto);
        movement.setPlayer(dto.getPlayerEnum());
        Game game = movementService.saveMoviment(movement).getGame();
        return this.checksIfMatchIsOver(game, dto.getPlayerEnum());
    }

    private EndGameDTO checksIfMatchIsOver(Game game, Player player) {
        boolean isDraw = this.movementService.checkDrawGame(game.getId());
        Result result = Result.valueOf(player.name());
        EndGameDTO msg = null;

        if(isDraw) {
            msg = new EndGameDTO(MATCH_ENDED, Result.DRAW);
            game.setStatus(Status.DRAW);
        }

        boolean win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 0, 1, 0, 2, 0);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 1, 1, 1, 2, 1);

        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 2, 1, 2, 2, 2);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 2, 01, 1, 0, 0);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 1, 2, 1, 1, 1, 0);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 2, 2, 2, 1, 2, 0);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 2, 1, 1, 2, 0);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        win = this.movementService.checkByPositionsAndGameIdAndGamePlayer
                (game.getId(), player, 0, 0, 1, 1, 2, 2);
        if(win) {
            msg = new EndGameDTO(MATCH_ENDED, result);
            game.setStatus(Status.WINNER);
        }

        game.setNextPlayer(game.getNextPlayer() == Player.O ? Player.X : Player.O);
        this.repository.save(game);
        return msg;
    }


    private Movement validate(MovementInsertDTO dto) {
        UnhealthyDataException ex = new UnhealthyDataException();
        Movement movement = null;

        if (dto.getPlayer() == null || !Player.checkIfValidString(dto.getPlayer())) {
            ex.addMessage(INVALID_PLAYER);
            throw ex;
        }

        if (dto.getPosition() == null || dto.getPosition().isEmptyCoordinate()) {
            ex.addMessage(INVALID_COORDINATE);
            throw ex;
        }

        Optional<GameStatusDTO> statusGameOptional = this.repository.findNextGameStatusByHash(dto.getId());

        if (!statusGameOptional.isPresent()) {
            ex.addMessage(MATCH_NOT_FOUND);
            throw ex;
        } else {
            GameStatusDTO statusGame = statusGameOptional.get();
            if (!statusGame.getNextPlayer().equals(dto.getPlayerEnum())) {
                ex.addMessage(IT_IS_NOT_PLAYER_TURN);
                throw ex;
            }

            if (statusGame.isGameOver()) {
                ex.addMessage(MATCH_ENDED);
                throw ex;
            }

            movement = this.movementService.getByGameHashAndPositions
                    (dto.getId(), dto.getPosition().getX(), dto.getPosition().getY());

            if (movement.getPlayer() != null) {
                ex.addMessage(MOVEMENT_ALREADY_PERFORMED);
                throw ex;
            }

        }

        return movement;
    }
}
