package microtope.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.worker.ValueChecker;

public class SQLConfig {
	private static Logger logger = LogManager.getLogger(SQLConfig.class);

    public String adress_to_connect = null;
    public String port_to_connect = null;
    public String database_to_connect = null;
    public String user_to_connect=null;
    public String pwd_to_connect=null;
    
    
    // Both Constructors are private to use the Factory Methods!
    
    //Empty Constructor to yield default values
    private SQLConfig() {}
    
    //Constructor with Args
    private SQLConfig(String address, String port, String database, String user, String pwd) {
    	adress_to_connect=address;
    	port_to_connect=port;
    	database_to_connect=database;
    	user_to_connect=user;
    	pwd_to_connect=pwd;
    }
    
    public static SQLConfig createSQLConfigFromArgs(String[] args) {
        if(args.length!=5) {
        	logger.error( "Did not get enough args for SQLConfig!" );
        	return new SQLConfig();
        }
        
        var adress_to_check = args[0];
    	var port_to_check = args[1];
    	var queue_to_check = args[2];
    	if(adress_to_check==null || adress_to_check.isEmpty()) {
    		logger.error( "Recieved null or empty Adress" );
        	return new SQLConfig();
    	}
    	if(port_to_check==null || port_to_check.isEmpty()) {
    		logger.error( "Recieved null or empty port" );
        	return new SQLConfig();
    	}
    	if(queue_to_check==null || queue_to_check.isEmpty()) {
    		logger.error( "Recieved null or empty topic" );
        	return new SQLConfig();
    	}
    	
    	if(!ValueChecker.goodURL(adress_to_check)) {
    		logger.warn( adress_to_check + " does not look like valid IP Adress or Domain Name!");
    		logger.warn("trying to connect to " + adress_to_check + " anyway...");
    	}
    	if(!ValueChecker.goodPort(port_to_check)) {
    		logger.error( port_to_check + " is not a valid Port!");
    		return new SQLConfig();
        }
    	//TODO: ValueChecker for Queue-Names?
    	logger.info( "args[] are ok, starting sender ..." );
    	
    	return(new SQLConfig(adress_to_check,port_to_check,queue_to_check,args[3],args[4]));
    }
    
    public static SQLConfig emptyConfig() {
    	return new SQLConfig();
    }
    
    public boolean isEmpty() {
    	   return adress_to_connect == null &&
    	    port_to_connect == null &&
    		database_to_connect == null;
   }
    
}
