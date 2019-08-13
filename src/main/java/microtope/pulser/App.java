package microtope.pulser;

import java.io.IOException;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws JMSException, IOException, InterruptedException
    {
        logger.info( "Starting AMQ-Sender" );
        logger.debug( "Recieved " + args + " as arguments" );
        
        String adress_to_connect = null;
        String port_to_connect = null;
        String queue_to_connect = null;
        String user_to_connect=null;
        String pwd_to_connect=null;
        
        if(args.length!=5) {
        	logger.error( "Did not get enough args!" );
        	logger.error( "The args have to be: ActiveMQ_IP ActiveMQ_Port ActiveMQ_Queue ActiveMQ_User ActiveMQ_Pwd" );
        	return;
        }
        else {
        	var adress_to_check = args[0];
        	var port_to_check = args[1];
        	var queue_to_check = args[2];
        	if(!ValueChecker.goodURL(adress_to_check)) {
        		logger.warn( adress_to_check + " does not look like valid IP Adress or Domain Name!");
        		logger.warn("trying to connect to " + adress_to_check + " anyway...");
        	}
        	if(!ValueChecker.goodPort(port_to_check)) {
        		logger.error( port_to_check + " is not a valid Port!");
        		return;
        	}
        	//TODO: ValueChecker for Queue-Names?
        	logger.info( "args[] are ok, starting sender ..." );
        	
        	adress_to_connect = adress_to_check;
        	port_to_connect = port_to_check;
        	queue_to_connect = queue_to_check;
        	user_to_connect=args[3];
        	pwd_to_connect=args[4];
        	
        }
        
        
        AMQMessageSender sender = new AMQMessageSender(adress_to_connect,port_to_connect, queue_to_connect,user_to_connect,pwd_to_connect);
        
        sender.sendMessage("v0.0.8 message");
        
        logger.info( "Creating Worker...");
        Worker w = Worker.randomWorker(sender, 50);

        logger.info( "Created Worker. Start working.");
        w.work();
        
        while(!w.finished)
        {
        	logger.trace("waiting for worker");
        }
        logger.info("Worker finished");
        
        sender.close();
        
        logger.info( "Closing AMQ-Sender" );
    }
}
