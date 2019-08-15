Use `MicroTope`;

CREATE VIEW MicroTope.steps_by_user 
AS
SELECT ps.player_id,SUM(steps) AS steps 
FROM players AS ps
JOIN steps AS s
ON ps.player_id = s.player_id
GROUP BY ps.player_id 
ORDER BY steps DESC;

CREATE VIEW MicroTope.coins_by_user
AS 
SELECT player_id, sum(value) AS coins
FROM coins 
GROUP BY player_id
ORDER BY coins DESC;

CREATE VIEW MicroTope.coins_by_team 
AS
SELECT t.team_id, t.team_name, SUM(value) AS coins
FROM teams AS t
JOIN players AS ps 
JOIN coins AS c
ON t.team_id = ps.team_id
AND ps.player_id = c.player_id
GROUP BY t.team_id 
ORDER BY coins DESC;

CREATE VIEW MicroTope.steps_by_team
AS 
SELECT t.team_id,t.team_name,sum(steps) AS steps
FROM teams as t 
JOIN steps as s
JOIN players as ps 
ON ps.team_id = t.team_id 
AND s.player_id = ps.player_id
GROUP BY t.team_id 
ORDER BY steps DESC;