"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
const mariadb = __importStar(require("mariadb"));
const ConfigLog4j_1 = require("../logging/ConfigLog4j");
exports.register = (app, host, user, pwd, port, dbname) => {
    const log = ConfigLog4j_1.factory.getLogger("routes/api");
    app.get(`/api/healthcheck`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT status FROM health;");
            log.info(rows); // [ {val: 1}, meta: ... ]
            res.send(JSON.stringify(rows));
        }
        catch (err) {
            res.send("healthcheck for mariadb failed!");
            log.error("healthcheck for mariadb failed!", err);
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/players/all`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT player_id,team_id FROM players;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get players", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/player_summary/all`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT player_id,player_name, steps, coins FROM player_summary;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get players", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/teams`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT team_id,team_name FROM teams;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get teams", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/coins_by_team`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT team_name, coins FROM coins_by_team;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get coins_by_team", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/steps_by_team`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT team_name, steps FROM steps_by_team;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get steps_by_team", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/steps_by_player/all`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT player_id, steps FROM steps_by_user;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get steps_by_user", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
    app.get(`/api/coins_by_player/all`, (req, res) => __awaiter(void 0, void 0, void 0, function* () {
        let conn;
        try {
            conn = yield mariadb.createConnection({ host, user, password: pwd, database: dbname, port });
            const rows = yield conn.query("SELECT player_id, coins FROM coins_by_user;");
            res.send(rows);
        }
        catch (err) {
            log.error("failed to get coins_by_user", err);
            res.status(500);
            res.send();
        }
        finally {
            if (conn) {
                conn.end();
            }
        }
    }));
};
//# sourceMappingURL=api.js.map