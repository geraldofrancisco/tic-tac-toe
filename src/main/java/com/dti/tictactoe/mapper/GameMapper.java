package com.dti.tictactoe.mapper;

import com.dti.tictactoe.dtos.GameDTO;
import com.dti.tictactoe.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper extends EntityMapper<GameDTO, Game> {

    @Mapping(target = "id", source = "hash")
    @Mapping(target = "firstPlayer", source = "nextPlayer")
    GameDTO toDto(Game entity);
}
