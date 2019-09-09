USE 'microtope';

CREATE USER IF NOT EXISTS 'worker'@'%' IDENTIFIED BY 'INeed2Write';

CREATE USER IF NOT EXISTS 'api'@'%' IDENTIFIED BY 'Need4Read';

CREATE USER IF NOT EXISTS 'auditor'@'%' IDENTIFIED BY 'ARGU5';

CREATE USER IF NOT EXISTS 'docker_health'@'localhost' IDENTIFIED BY 'showmelove';

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'admicromin';

FLUSH PRIVILEGES;