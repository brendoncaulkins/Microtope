package microtope.worker;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MariaDBWriter implements Closeable{
	
	Connection con;
	private static Logger logger = LogManager.getLogger(MariaDBWriter.class);

	public MariaDBWriter(String adress, String port, String user, String pwd) throws SQLException {
		var url = String.format("jdbc:mysql://%s:%s/%s",adress,port,"Microworld");
		logger.info("Building MariaDB connection to URL " + url);
		
		Properties connectionProps = new Properties();
	    connectionProps.put("user", user);
	    connectionProps.put("password", pwd);
	    
	    con = DriverManager.getConnection(url,connectionProps);
	
	    logger.info("Connection to " + url + " established");
	    
	    healthcheck();
	    
	    con.close();
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
	public void close() throws IOException {
		try {
			con.close();
			logger.debug("Closed MariaDBWriter DB Connection successfully");
		} catch (SQLException e) {
			logger.error(e);
		}
	}
	
}
