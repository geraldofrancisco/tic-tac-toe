package com.dti.tictactoe.repository;

import com.dti.tictactoe.dtos.GameStatusDTO;
import com.dti.tictactoe.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query(value =  "SELECT new com.dti.tictactoe.dtos.GameStatusDTO(g.nextPlayer, g.status) " +
                    "FROM Game g WHERE g.hash = :hash")
    Optional<GameStatusDTO> findNextGameStatusByHash(@Param("hash") String hash);
}
