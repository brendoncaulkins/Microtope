package microtope.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.pulser.ValueChecker;

public class ActiveMQConfig {
	private static Logger logger = LogManager.getLogger(ActiveMQConfig.class);

    public String adress_to_connect = null;
    public String port_to_connect = null;
    public String queue_to_connect = null;
    public String user_to_connect=null;
    public String pwd_to_connect=null;
    
    
    // Both Constructors are private to use the Factory Methods!
    
    //Empty Constructor to yield default values
    private ActiveMQConfig() {}
    
    //Constructor with Args
    private ActiveMQConfig(String address, String port, String queue, String user, String pwd) {
    	adress_to_connect=address;
    	port_to_connect=port;
    	queue_to_connect=queue;
    	user_to_connect=user;
    	pwd_to_connect=pwd;
    }
    
    public static ActiveMQConfig createActiveMQConfigFromArgs(String[] args) {
        if(args.length!=5) {
        	logger.error( "Did not get enough args!" );
        	logger.error( "The args have to be: ActiveMQ_IP ActiveMQ_Port ActiveMQ_Queue ActiveMQ_User ActiveMQ_Pwd" );
        	return new ActiveMQConfig();
        }
        
        var adress_to_check = args[0];
    	var port_to_check = args[1];
    	var queue_to_check = args[2];
    	if(!ValueChecker.goodURL(adress_to_check)) {
    		logger.warn( adress_to_check + " does not look like valid IP Adress or Domain Name!");
    		logger.warn("trying to connect to " + adress_to_check + " anyway...");
    	}
    	if(!ValueChecker.goodPort(port_to_check)) {
    		logger.error( port_to_check + " is not a valid Port!");
    		return new ActiveMQConfig();
        }
    	//TODO: ValueChecker for Queue-Names?
    	logger.info( "args[] are ok, starting sender ..." );
    	
    	return(new ActiveMQConfig(adress_to_check,port_to_check,queue_to_check,args[3],args[4]));
    }
    
    public static ActiveMQConfig emptyConfig() {
    	return new ActiveMQConfig();
    }
    
    public boolean isEmpty() {
    	   return adress_to_connect == null &&
    	    port_to_connect == null &&
    	    queue_to_connect == null;
   }
    
}
