"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
// import * as cors from "cors";
const cors = require("cors");
const express_1 = __importDefault(require("express"));
const ConfigLog4j_1 = require("./logging/ConfigLog4j");
const indexRoutes = __importStar(require("./routes/index"));
const log = ConfigLog4j_1.factory.getLogger("index");
const app = express_1.default();
let exposedPort = 8080; // default exposedPort to listen
log.info("Starting API ... ");
// Configure Express to parse incoming JSON data
app.use(express_1.default.json());
app.use(cors());
/*
* The process args [0] is the node exe, the process args[1] is the file running
* This is a little strange behaviour, but what in node is not?
* This is why the args-array-logic starts with [2]
*/
if (process.argv.length === 2) {
    log.error("Did not find any system args - Shutting down!");
}
else {
    // ${DBMariaDB_Adress} ${MariaDB_User} ${MariaDB_PW} ${MariaDB_Port} ${MariaDB_DatabaseName}
    log.debug("Found Systemargs ... ");
    const DBHost = process.argv[2];
    const DBUser = process.argv[3];
    const DBPwd = process.argv[4];
    const DBPort = Number.parseInt(process.argv[5], 10);
    const DBName = process.argv[6];
    exposedPort = Number.parseInt(process.argv[7], 10);
    log.info("Connecting to Host:" + DBHost + ":" + DBPort);
    log.info(" as User " + DBUser + " with PWD " + "[[REDACTED]]" + " on DB " + DBName);
    indexRoutes.register(app, DBHost, DBUser, DBPwd, DBPort, DBName);
}
// start the Express server
app.listen(exposedPort, () => {
    log.info(`server started at http://localhost:${exposedPort}`);
});
//# sourceMappingURL=index.js.map