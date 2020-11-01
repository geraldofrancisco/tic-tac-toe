package com.dti.tictactoe.resources;

import com.dti.tictactoe.dtos.EndGameDTO;
import com.dti.tictactoe.dtos.GameDTO;
import com.dti.tictactoe.dtos.MovementInsertDTO;
import com.dti.tictactoe.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameResource {

    @Autowired
    private GameService service;

    @PostMapping
    public ResponseEntity<GameDTO> createGame() {
        return ResponseEntity.ok(this.service.createGame());
    }

    @PostMapping("/{id}/movement")
    public ResponseEntity<EndGameDTO> createMoviment(@PathVariable String id, @RequestBody MovementInsertDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(this.service.makePlay(dto));
    }
}
