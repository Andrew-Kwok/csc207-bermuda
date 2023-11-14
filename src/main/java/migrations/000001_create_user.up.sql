CREATE TABLE IF NOT EXISTS `user` (
    id char(36) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    user_level int NOT NULL DEFAULT 1,

    PRIMARY KEY (id),
    UNIQUE KEY (username)
);