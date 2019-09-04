package microtope.worker;

import java.io.IOException;
import java.sql.SQLException;
import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.config.ActiveMQConfig;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws IOException, InterruptedException, SQLException
    {
        logger.info( "Starting Worker" );
        
        ActiveMQConfig amqconf = ActiveMQConfig.emptyConfig();
        
        String db_adress_to_connect=null;
        String db_port_to_connect=null;
        String db_name_to_connect=null;
        String db_user_to_connect=null;
        String db_pwd_to_connect=null;
        
        if(args.length!=10) {
        	logger.error( "Did not get enough args!" );
        	logger.error( "The args have to be: ActiveMQ_IP ActiveMQ_Port ActiveMQ_Queue ActiveMQ_User ActiveMQ_Pwd" );
        	return;
        }
        else {
        	if(!ValueChecker.goodPort(args[6])) {
        		logger.error( args[6] + " is not a valid Port for the Database!");
        		return;
        	}
        	
        	if(args[8] == null ) {
        		logger.warn("Recieved null or empty as db user - trying to connect as anonymus");
        		if(args[9]!=null || !args[4].isEmpty()) {
        			logger.error("recieved a password for db without a user!");
        			return;
        		}
        	}
        	
        	String[] amqargs = takeFirstN(args,5);
        	amqconf = ActiveMQConfig.createActiveMQConfigFromArgs(amqargs);
        	
        	
            db_adress_to_connect=args[5];
            db_port_to_connect=args[6];
            db_name_to_connect=args[7];
            db_user_to_connect=args[8];
            db_pwd_to_connect=args[9];
            
            logger.info( "args[] are ok, starting worker ..." );

            try {
				MessageReciever rec = new AMQMessageReciever(amqconf);

	            var mariadbwriter = new MariaDBWriter(db_adress_to_connect, db_port_to_connect,db_name_to_connect, db_user_to_connect, db_pwd_to_connect);
	            var listener = new DBInsertListener(mariadbwriter);
	            
	            rec.registerMessageListener(listener);
	            
            } catch (JMSException e) {
				logger.error("Opening the MessageReciever gone wrong! Closing Application with Error");
				System.exit(1);
			}
            
            
            logger.info("mariadb writer worked properly, ending testrun");
        }
                
        logger.info( "Closing AMQ-Reciever" );
    }
    

    private static String[] takeNtoM(String[] args, int n, int m) {
    	String[] toGet = new String[m-n];
    	for(int i = n; i<m;i++)
    		toGet[i]=args[i];
    	return toGet;
    }
    
    private static String[] takeFirstN(String[] args, int n) {
    	return takeNtoM(args,0,n);
    }

}