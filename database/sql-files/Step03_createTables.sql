USE `microtope`;

CREATE TABLE IF NOT EXISTS teams (
    team_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(20) NOT NULL,
    valid_from TIMESTAMP(6) GENERATED ALWAYS AS ROW START NOT NULL,
    valid_to   TIMESTAMP(6) GENERATED ALWAYS AS ROW END   NOT NULL,
    PERIOD FOR SYSTEM_TIME (valid_from, valid_to),
    CONSTRAINT constraint_unique_teamnames UNIQUE (team_name)
)WITH SYSTEM VERSIONING, engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS players (
  player_id INT UNSIGNED NOT NULL PRIMARY KEY ,
  player_name VARCHAR(30),
  valid_from TIMESTAMP(6) GENERATED ALWAYS AS ROW START NOT NULL,
  valid_to   TIMESTAMP(6) GENERATED ALWAYS AS ROW END   NOT NULL,
  PERIOD FOR SYSTEM_TIME (valid_from, valid_to),
  team_id INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_players_teams`
		FOREIGN KEY (team_id) REFERENCES teams (team_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
)WITH SYSTEM VERSIONING, engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS steps (
  `player_id` INT UNSIGNED NOT NULL,
  valid_from TIMESTAMP(6) GENERATED ALWAYS AS ROW START NOT NULL,
  valid_to   TIMESTAMP(6) GENERATED ALWAYS AS ROW END   NOT NULL,
  PERIOD FOR SYSTEM_TIME (valid_from, valid_to),
  `steps` INT UNSIGNED  NOT NULL,
  `recorded` DATETIME NOT NULL,
  CONSTRAINT `fk_steps_players`
    FOREIGN KEY (player_id) REFERENCES players (player_id)
    ON DELETE CASCADE
)WITH SYSTEM VERSIONING, engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS coins (
  `player_id` INT UNSIGNED NOT NULL,
  valid_from TIMESTAMP(6) GENERATED ALWAYS AS ROW START NOT NULL,
  valid_to   TIMESTAMP(6) GENERATED ALWAYS AS ROW END   NOT NULL,
  PERIOD FOR SYSTEM_TIME (valid_from, valid_to),
  `value` INT UNSIGNED  NOT NULL,
  `recorded` DATETIME NOT NULL,
  CONSTRAINT `fk_coins_players`
    FOREIGN KEY (player_id) REFERENCES players (player_id)
    ON DELETE CASCADE
)WITH SYSTEM VERSIONING, engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS audits (
  `audit_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `valid_from` TIMESTAMP(6) GENERATED ALWAYS AS ROW START NOT NULL,
  `valid_to`   TIMESTAMP(6) GENERATED ALWAYS AS ROW END   NOT NULL,
  PERIOD FOR SYSTEM_TIME (valid_from, valid_to),
  `player_id` INT UNSIGNED NOT NULL,
  `action` ENUM('login','logout') NOT NULL,
  `recorded` DATETIME NOT NULL,
  `controlled` BOOLEAN NOT NULL DEFAULT 0,
  CONSTRAINT `fk_auditing_players`
    FOREIGN KEY (player_id) REFERENCES players (player_id)
    ON DELETE CASCADE
)WITH SYSTEM VERSIONING, engine=InnoDB default charset utf8;
