CREATE TABLE game(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    hash VARCHAR(32) NOT NULL,
    next_player VARCHAR(1) NOT NULL,
    winner VARCHAR(1),
    status VARCHAR(7) NOT NULL
)