import * as express from "express";
import * as api_route from "./api";
import * as player_route from "./player";
import * as team_route from "./team";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {
    // API does "only" healthcheck
    api_route.register( app, host, user, pwd, port, dbname );
    // Everything related to teams
    team_route.register( app, host, user, pwd, port, dbname );
    // Everything related to players
    player_route.register( app, host, user, pwd, port, dbname );

};
