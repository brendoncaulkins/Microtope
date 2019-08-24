Use 'microtope';

GRANT ALL PRIVILEGES ON microtope.* TO 'admin'@'%';

/*
To Produce this list login as admin and run:
SELECT CONCAT("GRANT SELECT ON  microtope.", table_name, " TO 'api'@'%';")
FROM information_schema.TABLES
WHERE table_schema = "microtope" AND table_name <> "audits";
*/
GRANT SELECT ON  microtope.coins TO 'api'@'%';                       
GRANT SELECT ON  microtope.players TO 'api'@'%';                     
GRANT SELECT ON  microtope.steps_by_team TO 'api'@'%';               
GRANT SELECT ON  microtope.steps_by_user TO 'api'@'%';               
GRANT SELECT ON  microtope.teams TO 'api'@'%';           
GRANT SELECT ON  microtope.player_summary TO 'api'@'%';              
GRANT SELECT ON  microtope.coins_by_team TO 'api'@'%';               
GRANT SELECT ON  microtope.steps TO 'api'@'%';                       
GRANT SELECT ON  microtope.coins_by_user TO 'api'@'%';
/*
To Produce this list login as admin and run:
SELECT CONCAT("GRANT SELECT,INSERT,DELETE,UPDATE ON  microtope.", table_name, " TO 'worker'@'%';")
FROM information_schema.TABLES
WHERE table_type="BASE TABLE" AND table_schema = "microtope" AND table_name <> "audits";
*/
GRANT SELECT,INSERT,DELETE,UPDATE ON  microtope.coins TO 'worker'@'%';                      
GRANT SELECT,INSERT,DELETE,UPDATE ON  microtope.players TO 'worker'@'%';                    
GRANT SELECT,INSERT,DELETE,UPDATE ON  microtope.teams TO 'worker'@'%';                 
GRANT SELECT,INSERT,DELETE,UPDATE ON  microtope.steps TO 'worker'@'%';  

-- Health Checks
GRANT SELECT ON microtope.health TO 'worker'@'%';
GRANT SELECT ON microtope.health TO 'api'@'%';
GRANT SELECT ON microtope.health TO 'auditor'@'%';

GRANT INSERT,UPDATE (player_name) ON microtope.players TO 'api'@'%';

-- "HIGH SECURITY" LOGIN/LOGOUT TABLE RELATED

GRANT INSERT(player_id,recorded,action) ON microtope.audits TO 'worker'@'%';
-- Auditor can read everything and update/write the controlled field to true
GRANT INSERT,UPDATE (controlled) ON microtope.audits TO 'auditor'@'%';
GRANT SELECT ON microtope.audits to 'auditor'@'%';

FLUSH PRIVILEGES;