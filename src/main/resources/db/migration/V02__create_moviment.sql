CREATE TABLE movement(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id BIGINT NOT NULL,
    player VARCHAR(1),
    position_x INTEGER,
    position_Y INTEGER,

    CONSTRAINT FK_GAME FOREIGN KEY (game_id) REFERENCES game(id)
)