import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";
import {IPlayer} from "../models/player.model";
import {closeConnIfExists, send500Error} from "../shared/commons";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {

    const log = factory.getLogger("routes/player");

    app.get( `/api/player`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id as id,team_id FROM players;");
          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get( `/api/player/:id`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const id: number = parseInt(req.params.id, 10);
          const rows = await conn.query("SELECT player_id AS id,team_id FROM players WHERE player_id = (?);", [id]);
          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.put( "/api/player/:id", async (req: express.Request, res: express.Response) => {
      return 5;
    });

    app.get( `/api/player_summary`,  async ( req: express.Request, res: express.Response ) => {
      let conn;
      try {
        conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
        const rows = await conn.query("SELECT player_id AS id,player_name AS name, steps, coins FROM player_summary;");
        res.send(rows);
      } catch (err) {
        send500Error(err, res);
      } finally {
        closeConnIfExists(conn);
      }
    } );

    app.get( `/api/player_summary/:id`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const id: number = parseInt(req.params.id, 10);
          const rows = await conn.query("SELECT player_id AS id,player_name AS name, steps, coins FROM player_summary WHERE player_id = (?);", [id]);
          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
      } );

    app.get(`/api/steps_by_player`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id AS id, steps FROM steps_by_user;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get(`/api/coins_by_player`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id AS id, coins FROM coins_by_user;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

};
