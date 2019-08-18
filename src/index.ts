import express from "express";
import {factory} from "./logging/ConfigLog4j";
import * as indexRoutes from "./routes/index";

const log = factory.getLogger("index");

const app = express();
const port = 8080; // default port to listen

// Configure Express to parse incoming JSON data
app.use( express.json() );

indexRoutes.register(app);

// start the Express server
app.listen( port, () => {
    log.info( `server started at http://localhost:${ port }` );
} );
