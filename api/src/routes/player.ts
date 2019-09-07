import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";
import {closeConnIfExists, send500Error} from "../shared/commons";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {

    const log = factory.getLogger("routes/player");

    app.get( `/api/player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id,team_id FROM players;");
          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get( `/api/player_summary`,  async ( req: any, res ) => {
      let conn;
      try {
        conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
        const rows = await conn.query("SELECT player_id,player_name, steps, coins FROM player_summary;");
        res.send(rows);
      } catch (err) {
        send500Error(err, res);
      } finally {
        closeConnIfExists(conn);
      }
  } );

    app.get(`/api/steps_by_player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id, steps FROM steps_by_user;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get(`/api/coins_by_player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT player_id, coins FROM coins_by_user;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

};
