Use microtope;

GRANT USAGE ON microtope.* to 'worker'@'%';

GRANT ALL PRIVILEGES ON microtope.* TO 'admin'@'%';

GRANT SELECT ON microtope.* TO 'api'@'%';


FLUSH PRIVILEGES;