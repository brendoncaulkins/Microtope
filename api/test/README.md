# API Tests

This folder will contain automated tests and maybe other sources required later.

At the moment, I'll just list some helpfull curl statements.

## Prereq

I'm running these *tests* from console with curl, after starting the whole microservice architecture with the [toplevel compose](../../meta/docker-compose.yml).

Maybe some tests will already be included.

## Known Values

PlayerIDs: 100001, 100002

TeamIDs: 1,2,3,4

LoginDates: To Be Added here

## Curl

The given Statements beyond should all return values - if any of those does not, there is a problem.

### General Health

`curl localhost:3000/api/healthcheck`

This will return an json object with "status:alive".
The healthcheck also requires the database and will therefore fail if the database is unreachable.

### Players

`curl localhost:3000/api/player/`

`curl localhost:3000/api/player/100002`

`curl localhost:3000/api/player_summary`

The following does not return a value, but a positive statuscode. Check the result with player_summary curl.

`curl  --header "Content-Type: application/json" --request PUT --data '{"id":100002,"name":"Curly Boy"}'   http://localhost:3000/api/player/100002`

### Teams

`curl localhost:3000/api/team/`

`curl localhost:3000/api/team/1`

`curl localhost:3000/api/team_summary`

The following does not return a value, but a positive statuscode. Check the result with team_summary curl.

`curl  --header "Content-Type: application/json" --request PUT --data '{"id":1,"name":"NameChangers"}'   http://localhost:3000/api/team/1`
