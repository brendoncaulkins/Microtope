package microtope.worker;

import java.io.IOException;
import java.sql.SQLException;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws IOException, InterruptedException, SQLException
    {
        logger.info( "Starting AMQ-Reciever" );
        logger.debug( "Recieved " + args + " as arguments" );
        
        String amq_adress_to_connect = null;
        String amq_port_to_connect = null;
        String amq_queue_to_connect = null;
        String amq_user_to_connect=null;
        String amq_pwd_to_connect=null;
        
        String db_adress_to_connect=null;
        String db_port_to_connect=null;
        String db_user_to_connect=null;
        String db_pwd_to_connect=null;
        
        // timeout between pulls in ms - standard is 0.5 second
        // timeout only resembles the sleeptime, when woke the reciever pulls everything
        int timeout=500;
        
        if(args.length!=10) {
        	logger.error( "Did not get enough args!" );
        	logger.error( "The args have to be: ActiveMQ_IP ActiveMQ_Port ActiveMQ_Queue ActiveMQ_User ActiveMQ_Pwd" );
        	return;
        }
        else {
        	/*
        	 * Check URLs, only warn if they look strange
        	 */
        	if(!ValueChecker.goodURL(args[0])) {
        		logger.warn( args[0] +  " does not look like valid URL for AMQ!");
        	}
        	if(!ValueChecker.goodURL(args[5])) {
        		logger.warn( args[5] +  " does not look like valid URL for the Database!");
        	}
        	/*
        	 * Check Ports, exit if bad
        	 */
        	if(!ValueChecker.goodPort(args[1])) {
        		logger.error( args[1] + " is not a valid Port for AMQ!");
        		return;
        	}
        	if(!ValueChecker.goodPort(args[6])) {
        		logger.error( args[6] + " is not a valid Port for the Database!");
        		return;
        	}
        	/*
        	 * Check Users - warn if empty
        	 * Exit if the User is empty but Password is not
        	 */
        	if(args[3] == null | args[3].isEmpty()) {
        		logger.warn("Recieved null or empty as amq user - trying to connect as anonymus");
        		if(args[4]!=null || !args[4].isEmpty()) {
        			logger.error("recieved a password for amq without a user!");
        			return;
        		}
        	}
        	
        	if(args[7] == null | args[3].isEmpty()) {
        		logger.warn("Recieved null or empty as db user - trying to connect as anonymus");
        		if(args[8]!=null || !args[4].isEmpty()) {
        			logger.error("recieved a password for db without a user!");
        			return;
        		}
        	}
        	/*
        	 * Check Timeout to be a positve Integer
        	 */
        	timeout = Integer.parseInt(args[9]);
            if(timeout==0) {
            	logger.error("recieved timeout of 0 ms! shutting done as this would be destructive!");
            	return;
            }
            if(timeout<0) {
            	logger.error("recieved negative timeout");
            	return;
            }
            if(timeout<100) {
            	logger.warn("Reciever timeout of " + timeout + " ms, this is quite fast.");
            }
        	
        	//TODO: ValueChecker for Queue-Names?
        	logger.info( "args[] are ok, starting sender ..." );
        	
        	amq_adress_to_connect = args[0];
            amq_port_to_connect = args[1];
            amq_queue_to_connect = args[2];
            amq_user_to_connect=args[3];
            amq_pwd_to_connect=args[4];
            
            db_adress_to_connect=args[5];
            db_port_to_connect=args[6];
            db_user_to_connect=args[7];
            db_pwd_to_connect=args[8];
        	
            logger.debug("Connection to amq " + amq_adress_to_connect + " as " + amq_user_to_connect + " with pwd [REDACTED] on port " + amq_port_to_connect + " on queue " + amq_queue_to_connect );
            
            try {
				MessageReciever rec = new AMQMessageReciever(amq_adress_to_connect, amq_port_to_connect,amq_queue_to_connect,amq_user_to_connect,amq_pwd_to_connect);
				rec.readMessage();
				rec.close();
            } catch (JMSException e) {
				logger.error("Opening the MessageReciever gone wrong! Closing Application with Error");
				System.exit(1);
			}
            
            logger.info("reciever worked properly, starting MariaDBWriter");
            var mariadbwriter = new MariaDBWriter(db_adress_to_connect, db_port_to_connect, db_user_to_connect, db_pwd_to_connect);
        
            logger.info("mariadb writer worked properly, ending testrun");
        }
                
        logger.info( "Closing AMQ-Reciever" );
    }
}
