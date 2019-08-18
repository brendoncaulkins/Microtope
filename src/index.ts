import express from "express";
import {factory} from "./logging/ConfigLog4j";
import * as indexRoutes from "./routes/index";

const log = factory.getLogger("index");

const app = express();
const port = 8080; // default port to listen

// Configure Express to parse incoming JSON data
app.use( express.json() );

if(process.argv.length===2){
    console.info("Did not find any system args - using defaults");
  }
  else {
    console.log("Found Systemargs ... ");
    const DBHost:string = process.argv[2];
    const DBUser:string = process.argv[3];
    const DBPwd:string = process.argv[4];
    const DBName:string = process.argv[5]; 
    console.log("Connecting to Host:" + DBHost + " as User:" + DBUser + " with PWD --REDACTED-- on DB:" + DBName);
    setDBConnectionPool( DBHost, DBUser, DBPwd, DBName );
}

indexRoutes.register(app,"db","api","Need2Read",3306);

// start the Express server
app.listen( port, () => {
    log.info( `server started at http://localhost:${ port }` );
} );
