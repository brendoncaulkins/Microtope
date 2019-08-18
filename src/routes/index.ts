import * as express from "express";
import * as api from "./api";

export const register = ( app: express.Application ) => {
    const oidc = app.locals.oidc;

    // define a route handler for the default home page
    app.get( "/", ( req: any, res ) => {
        res.send( "Hello to my API!" );
    } );

    api.register( app );
};
