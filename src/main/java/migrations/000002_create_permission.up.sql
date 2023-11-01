CREATE TABLE IF NOT EXISTS (
    id char(36) NOT NULL,
    user_id char(36) NOT NULL,
    project_id char(10) NOT NULL,
    permission_name varchar(50) NOT NULL,
    permission_description varchar(255) NOT NULL DEFAULT '',

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES `user`(id)
);