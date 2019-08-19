Use 'microtope';

GRANT SELECT,INSERT,DELETE,UPDATE ON microtope.* to 'worker'@'%';

GRANT ALL PRIVILEGES ON microtope.* TO 'admin'@'%';

GRANT SELECT ON microtope.* TO 'api'@'%';

GRANT INSERT,UPDATE (player_name) ON microtope.players TO 'api'@'%';

FLUSH PRIVILEGES;