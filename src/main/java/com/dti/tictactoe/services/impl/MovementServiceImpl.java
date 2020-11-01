package com.dti.tictactoe.services.impl;

import com.dti.tictactoe.dtos.PositionDTO;
import com.dti.tictactoe.enums.Player;
import com.dti.tictactoe.models.Game;
import com.dti.tictactoe.models.Movement;
import com.dti.tictactoe.models.Position;
import com.dti.tictactoe.repository.MovementRepository;
import com.dti.tictactoe.services.MovementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MovementServiceImpl implements MovementService {

    @PersistenceContext
    private EntityManager em;

    private final MovementRepository repository;

    public MovementServiceImpl(MovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movement getByGameHashAndPositions(String hash, Integer x, Integer y) {
        return this.repository.findByGameHashAndPositions(hash, x, y).orElse(null);
    }

    @Override
    public Movement saveMoviment(Movement entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Override
    public boolean checkByPositionsAndGameIdAndGamePlayer(Long gameId, Player player, Integer x1, Integer y1, Integer x2, Integer y2, Integer x3, Integer y3) {
        Integer count = this.repository.countByGameIdAndPlayerAndPositions
                (gameId,player,x1,y1,x2,y2,x3,y3);
        return count.equals(3);
    }

    @Override
    public boolean checkDrawGame(Long gameId) {
        Integer count = this.repository.countNotNullPlayerByGameId(gameId);
        return count.equals(9);
    }
}
