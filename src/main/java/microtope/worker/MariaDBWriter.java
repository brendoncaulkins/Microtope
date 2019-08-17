package microtope.worker;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.messages.CoinMessage;
import microtope.messages.StepMessage;

public class MariaDBWriter implements Closeable, DBWriter{
	
	Connection con;
	private static Logger logger = LogManager.getLogger(MariaDBWriter.class);

	public MariaDBWriter(String adress, String port, String databaseName, String user, String pwd) throws SQLException {
		var url = String.format("jdbc:mysql://%s:%s/%s",adress,port,databaseName);
		
	    logger.debug("Trying to connect to "+url+" as "+user+ " with Password [REDACTED] ");
	    
	    con = DriverManager.getConnection(url,user, pwd);
	    
	    logger.info("Connection to " + url + " established");
	    
	    healthcheck();
	}
	
	private void healthcheck() throws SQLException {
		logger.info("performing healthcheck for mariadb writer");
		if(con==null || con.isClosed())
			logger.error("connection is null or closed!");
		else {
			PreparedStatement stmt = con.prepareStatement("SELECT status from health;");
			
			logger.debug("executing prepared statement for healthcheck...");
		    ResultSet rs=stmt.executeQuery();
		    logger.debug("recieved resultset ... recieved:");
		    
		    while(rs.next()){  
		    	logger.info(rs.getString("status"));  
		    }
		    logger.info("healthcheck passed!");
		}
	}

	@Override
	public void writeSteps(StepMessage msg) {
		logger.debug("TODO: Insert Step Message");
	}
	
	@Override
	public void writePlayer(int player) throws SQLException {
		int teamid = 1;
		// This writes the player if it does not exist
		logger.debug("writing player " + player + " with team " + teamid);
		if(con==null || con.isClosed())
			logger.error("connection is null or closed!");
		else {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO players (player_id, team_id) VALUES (? , ?) ON DUPLICATE KEY UPDATE player_id=player_id;");
			
			stmt.setInt(1, player);
			stmt.setInt(2, teamid);
			
		    stmt.executeQuery();
		    
		    logger.debug("Creating Player worked - not sure if player already existed!");
		}
	}
	
	@Override
	public void writeCoins(CoinMessage msg) {
		logger.debug("TODO: Insert Coin Message");
	}
	
	
	@Override
	public void close() throws IOException {
		try {
			con.close();
			logger.debug("Closed MariaDBWriter DB Connection successfully");
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
}
