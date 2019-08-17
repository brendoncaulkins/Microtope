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
		try {
			if(con==null || con.isClosed())
				logger.error("connection is null or closed!");
			else {
				writePlayer(msg.getPlayer());
				logger.debug("Created Player - now inserting Steps");
				PreparedStatement stmt = con.prepareStatement("INSERT INTO steps (player_id, steps, recorded) VALUES (? , ?, ?);");
				
				stmt.setInt(1, msg.getPlayer());
				stmt.setInt(2, msg.getSteps());
				stmt.setDate(3, convertUtilToSql(msg.getTimeStamp()));
				
			    stmt.executeQuery();

				logger.debug("Inserted " + msg.getSteps() + " steps for player " + msg.getPlayer());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
	
	@Override
	public void writePlayer(int player) {
		int teamid = 1;
		// This writes the player if it does not exist
		logger.debug("writing player " + player + " with team " + teamid);
		try {
			if(con==null || con.isClosed())
				logger.error("connection is null or closed!");
			else {
				PreparedStatement stmt = con.prepareStatement("INSERT IGNORE INTO players (player_id, team_id) VALUES (? , ?)");
				
				stmt.setInt(1, player);
				stmt.setInt(2, teamid);
				
			    stmt.executeQuery();
			    
			    logger.debug("Creating Player worked - not sure if player already existed!");
			}
		}catch(SQLException e) {
			logger.error("Recieved SQL Exception while Creating Player " + player,e);
		}
	}
	
	@Override
	public void writeCoins(CoinMessage msg) {
		try {
			if(con==null || con.isClosed())
				logger.error("connection is null or closed!");
			else {
				writePlayer(msg.getPlayer());
				logger.debug("Created Player - now inserting Steps");
				PreparedStatement stmt = con.prepareStatement("INSERT INTO coins (player_id, value, recorded) VALUES (? , ?, ?);");
				
				stmt.setInt(1, msg.getPlayer());
				stmt.setInt(2, msg.getCoins());
				stmt.setDate(3, convertUtilToSql(msg.getTimeStamp()));
				
			    stmt.executeQuery();

				logger.debug("Inserted " + msg.getCoins() + " coins for player " + msg.getPlayer());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
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
	
	private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
