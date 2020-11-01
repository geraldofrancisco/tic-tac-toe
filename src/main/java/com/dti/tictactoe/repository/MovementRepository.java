package com.dti.tictactoe.repository;

import com.dti.tictactoe.enums.Player;
import com.dti.tictactoe.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    @Query(value =  "SELECT m FROM Movement m " +
                    "JOIN m.game g " +
                    "JOIN m.position p "+
                    "WHERE g.hash = :hash AND p.x = :x AND p.y = :y")
    Optional<Movement> findByGameHashAndPositions(
            @Param("hash") String hash, @Param("x") Integer x, @Param("y") Integer y);

    @Query(value =  "SELECT COUNT(m) FROM Movement m " +
            "JOIN m.game g " +
            "JOIN m.position p "+
            "WHERE g.id = :gameId AND m.player = :player AND " +
            "((p.x = :x1 AND p.y = :y1) OR (p.x = :x2 AND p.y = :y2) OR (p.x = :x3 AND p.y = :y3))")
    Integer countByGameIdAndPlayerAndPositions
            (@Param("gameId") Long gameId, @Param("player") Player player, @Param("x1") Integer x1,
             @Param("y1") Integer y1, @Param("x2") Integer x2, @Param("y2") Integer y2,
             @Param("x3") Integer x3, @Param("y3") Integer y3);

    @Query(value =  "SELECT COUNT(m) FROM Movement m JOIN m.game g " +
            "WHERE m.player IS NOT NULL AND g.id = :gameId" )
    Integer countNotNullPlayerByGameId(@Param("gameId") Long gameId);
}
