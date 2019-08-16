USE 'microtope';

CREATE USER IF NOT EXISTS 'worker'@'%' IDENTIFIED VIA mysql_native_password BY "INeed2Write";
GRANT select,insert, delete, update ON 'microtope'.* to 'worker'@'%';

CREATE USER IF NOT EXISTS 'api'@'%' IDENTIFIED BY 'Need4Read';
GRANT select ON 'microtope'.* TO 'api'@'%';

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'admicromin';
GRANT ALL PRIVILEGES ON 'microtope'.* TO 'admin'@'%';

FLUSH PRIVILEGES;