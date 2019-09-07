import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";
import {closeConnIfExists} from "../shared/commons";

export const register = ( app: express.Application, host: string, user: string,
                          pwd: string, port: number, dbname: string ) => {

    const log = factory.getLogger("routes/api");

    app.get(`/api/healthcheck`,  async ( req: any, res ) => {
        let conn;
        try {
          conn = await mariadb.createConnection({host, user, password: pwd, database: dbname, port});
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
};
