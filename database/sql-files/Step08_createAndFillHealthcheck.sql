USE `microtope`;

CREATE TABLE IF NOT EXISTS health (
    status VARCHAR(40) NOT NULL
) engine=InnoDB default charset utf8;

INSERT INTO health (status) VALUES ("alive");

GRANT SELECT ON microtope.health TO 'worker'@'%';

GRANT SELECT ON microtope.health TO 'api'@'%';

GRANT SELECT ON microtope.health TO 'docker_health'@'localhost';