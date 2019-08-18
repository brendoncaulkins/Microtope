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
          res.send("healthcheck for mariadb failed!");
          log.error("healthcheck for mariadb failed!", err);
        } finally {
          if (conn) {
            conn.end();
          }
        }
    });

    app.get( `/api/players/all`,  async ( req: any, res ) => {
        try {
            // const userId = req.userContext.userinfo.sub;
            const players = {Gay: "Hobo"};
            // DB QUERY
            /*await db.any( `
            SELECT
                    id
                    , brand
                    , model
                    , year
                    , color
                FROM    guitars
                WHERE   user_id = $[userId]
                ORDER BY year, brand, model`, { userId } );
            */
            return res.json( players );
        } catch ( err ) {
            log.error(err);
            res.json( { error: err.message || err } );
        }
    } );

};
