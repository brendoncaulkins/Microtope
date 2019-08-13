USE `Microworld`;

CREATE TABLE IF NOT EXISTS teams (
    team_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(20) NOT NULL,
    CONSTRAINT constraint_unique_teamnames UNIQUE (team_name)
) engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS users (
  user_id INT UNSIGNED NOT NULL PRIMARY KEY ,
  team_id INT UNSIGNED NOT NULL,
  CONSTRAINT `fk_user_team`
		FOREIGN KEY (team_id) REFERENCES teams (team_id)
		ON DELETE CASCADE
		ON UPDATE RESTRICT
) engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS steps (
  `user_id` INT UNSIGNED NOT NULL,
  `steps` INT UNSIGNED  NOT NULL,
  `recorded` DATETIME NOT NULL, 
  PRIMARY KEY (`user_id`, `recorded`),
  CONSTRAINT `fk_steps_users`
    FOREIGN KEY (user_id) REFERENCES users (user_id)
    ON DELETE CASCADE
) engine=InnoDB default charset utf8;

CREATE TABLE IF NOT EXISTS coins (
  `user_id` INT UNSIGNED NOT NULL,
  `value` INT UNSIGNED  NOT NULL,
  `recorded` DATETIME NOT NULL, 
  PRIMARY KEY (`user_id`,`recorded`),
  CONSTRAINT `fk_coins_users`
    FOREIGN KEY (user_id) REFERENCES users (user_id)
    ON DELETE CASCADE
) engine=InnoDB default charset utf8;