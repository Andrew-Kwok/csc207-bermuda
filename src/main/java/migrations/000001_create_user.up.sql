CREATE TABLE IF NOT EXISTS `user` (
    id CHAR(36) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_level INT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    UNIQUE KEY (username)
);
