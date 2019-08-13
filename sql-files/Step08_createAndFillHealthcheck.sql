USE `Microworld`;

CREATE TABLE IF NOT EXISTS health (
    status VARCHAR(40) NOT NULL
) engine=InnoDB default charset utf8;

INSERT INTO health (status) VALUES ("alive");

GRANT select ON Microworld.health to micro_writer;