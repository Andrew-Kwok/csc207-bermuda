CREATE TABLE IF NOT EXISTS permission (
    id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    project_id CHAR(10) NOT NULL,
    permission_name VARCHAR(50) NOT NULL,
    permission_description VARCHAR(255) NOT NULL DEFAULT '',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES `user` (id)
);
