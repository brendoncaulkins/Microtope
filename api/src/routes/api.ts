import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {

    const log = factory.getLogger("routes/api");

    app.get(`/api/healthcheck`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT status FROM health;");
          log.info(rows); // [ {val: 1}, meta: ... ]
          res.send(JSON.stringify(rows));
        } catch (err) {
          res.send("healthcheck for database failed!");
          log.error("healthcheck for mariadb failed!", err);
        } finally {
          closeConnIfExists(conn);
        }
    });

    app.get( `/api/player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT player_id,team_id FROM players;");
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get( `/api/player_summary`,  async ( req: any, res ) => {
      let conn;
      try {
        conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
        const rows = await conn.query("SELECT player_id,player_name, steps, coins FROM player_summary;");
        res.send(rows);
      } catch (err) {
        send500Error(err,res);
      } finally {
        closeConnIfExists(conn);
      }
  } );

    app.get( `/api/team`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT team_id,team_name FROM teams;");
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get( `/api/team/:id`,  async ( req: any, res ) => {
      let conn:mariadb.Connection;
      try {
        conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
        let id:number = req.params.id;
        const rows = await conn.query("SELECT team_id,team_name FROM teams WHERE team_id = (?);",[id]);
        res.send(rows);
      } catch (err) {
        send500Error(err,res);
      } finally {
        closeConnIfExists(conn);
      }
  } );

    app.get(`/api/coins_by_team`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT team_name, coins FROM coins_by_team;");
          
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );
    app.get(`/api/steps_by_team`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT team_name, steps FROM steps_by_team;");
          
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get(`/api/steps_by_player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT player_id, steps FROM steps_by_user;");
          
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get(`/api/coins_by_player`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host:host, user:user, password: pwd, database: dbname, port:port});
          const rows = await conn.query("SELECT player_id, coins FROM coins_by_user;");
          
          res.send(rows);
        } catch (err) {
          send500Error(err,res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    function send500Error(err:Error,res:any):void{
      log.error("failed to get coins_by_user",err);
      res.status(500);
      res.send();
    }

    function closeConnIfExists(conn:mariadb.Connection):void{
      if (conn) {
        conn.end();
      }
    }
};