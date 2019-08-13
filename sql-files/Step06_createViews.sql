Use Microworld;

CREATE VIEW Microworld.steps_by_user 
AS
SELECT u.user_id,SUM(steps) AS steps 
FROM users AS u
JOIN steps AS s
ON u.user_id = s.user_id
GROUP BY u.user_id 
ORDER BY steps DESC;

CREATE VIEW Microworld.coins_by_user
AS 
SELECT user_id, sum(value) AS coins
FROM coins 
GROUP BY user_id
ORDER BY coins DESC;

CREATE VIEW Microworld.coins_by_team 
AS
SELECT t.team_id, t.team_name, SUM(value) AS coins
FROM teams AS t
JOIN users AS u 
JOIN coins AS c
ON t.team_id = u.team_id
AND u.user_id = c.user_id
GROUP BY t.team_id 
ORDER BY coins DESC;

CREATE VIEW Microworld.steps_by_team
AS 
SELECT t.team_id,t.team_name,sum(steps) AS steps
FROM teams as t 
JOIN steps as s
JOIN users as u 
ON u.team_id = t.team_id 
AND s.user_id = u.user_id
GROUP BY t.team_id 
ORDER BY steps DESC;