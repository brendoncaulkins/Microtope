import * as express from "express";
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";

export const register = ( app: express.Application ) => {

    const log = factory.getLogger("routes/api");

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
