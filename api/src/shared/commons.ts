
import * as mariadb from "mariadb";
import {factory} from "../logging/ConfigLog4j";

const log = factory.getLogger("shared/common");

export function send500Error(err: Error, res: any): void {
    log.error("failed to get coins_by_user", err);
    res.status(500);
    res.send();
}

export function closeConnIfExists(conn: mariadb.Connection): void {
    if (conn) {
        conn.end();
    }
}
