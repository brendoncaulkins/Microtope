import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";

import {closeConnIfExists, send500Error} from "../shared/commons";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {

    const log = factory.getLogger("routes/team");

    app.get( `/api/team`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT team_id AS id,team_name AS name FROM teams;");
          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get( `/api/team/:id`,  async ( req: express.Request, res: express.Response ) => {
      let conn: mariadb.Connection;
      try {
        conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
        const id: number = parseInt(req.params.id, 10);
        const rows = await conn.query("SELECT team_id AS id,team_name AS name FROM teams WHERE team_id = (?);", [id]);
        res.send(rows);
      } catch (err) {
        send500Error(err, res);
      } finally {
        closeConnIfExists(conn);
      }
  } );

  app.put( "/api/team/:id", async (req: express.Request, res: express.Response) => {
    let conn;
    try {
      conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});

      const toChange = req.body;

      const id: number = parseInt(req.params.id, 10);
      if (toChange || toChange.id || toChange.id !== id) {
        send500Error(null, res);
      }

      await conn.query("UPDATE teams SET team_name = (?) WHERE team_id=(?);", [toChange.name, toChange.id]);

      res.status(102);
      res.send();

    } catch (err) {
      send500Error(err, res);
    } finally {
      closeConnIfExists(conn);
    }
  });

    app.get( `/api/team_summary`,  async ( req: express.Request, res: express.Response ) => {
    let conn;
    try {
      conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
      const rows = await conn.query("SELECT team_id AS id,team_name AS name, steps, coins FROM team_summary;");
      res.send(rows);
    } catch (err) {
      send500Error(err, res);
    } finally {
      closeConnIfExists(conn);
    }
  } );

    app.get( `/api/team_summary/:id`,  async ( req: express.Request, res: express.Response ) => {
      let conn;
      try {
        conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
        const id: number = parseInt(req.params.id, 10);
        const rows = await conn.query("SELECT team_id AS id,team_name AS name, steps, coins FROM team_summary WHERE team_id = (?);", [id]);
        res.send(rows);
      } catch (err) {
        send500Error(err, res);
      } finally {
        closeConnIfExists(conn);
      }
    } );

    app.get(`/api/coins_by_team`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT team_name AS name, coins FROM coins_by_team;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

    app.get(`/api/steps_by_team`,  async ( req: express.Request, res: express.Response ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
          const rows = await conn.query("SELECT team_name AS name, steps FROM steps_by_team;");

          res.send(rows);
        } catch (err) {
          send500Error(err, res);
        } finally {
          closeConnIfExists(conn);
        }
    } );

};
