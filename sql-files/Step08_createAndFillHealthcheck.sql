USE `microtope`;

CREATE TABLE IF NOT EXISTS health (
    status VARCHAR(40) NOT NULL
) engine=InnoDB default charset utf8;

INSERT INTO health (status) VALUES ("alive");

GRANT select ON microtope.health TO 'worker'@'%';

GRANT select ON microtope.health TO 'api'@'%';