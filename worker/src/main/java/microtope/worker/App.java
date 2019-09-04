package microtope.worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.config.ActiveMQConfig;
import microtope.config.SQLConfig;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws IOException, InterruptedException, SQLException, JMSException
    {
        logger.info( "Starting Worker" );
        
        ActiveMQConfig amqconf = ActiveMQConfig.emptyConfig();
        SQLConfig sqlconf = SQLConfig.emptyConfig();
        
        if(args.length!=10) {
        	logger.error( "The args have to be: ActiveMQ_IP ActiveMQ_Port ActiveMQ_Queue ActiveMQ_User ActiveMQ_Pwd" );
        	throw new IllegalArgumentException("Did not get enough args!");
        }
        else {
        	String[] amqargs = Arrays.copyOfRange(args, 0,5);
        	amqconf = ActiveMQConfig.createActiveMQConfigFromArgs(amqargs);
        	String[] sqlargs = Arrays.copyOfRange(args, 5, 10);
        	sqlconf = SQLConfig.createSQLConfigFromArgs(sqlargs);
        	
            logger.info( "args[] are ok, starting worker ..." );

			MessageReciever rec = new AMQMessageReciever(amqconf);
            var mariadbwriter = new MariaDBWriter(sqlconf);
            var listener = new DBInsertListener(mariadbwriter);
            
            rec.registerMessageListener(listener);
            
            logger.info("mariadb writer worked properly, ending testrun");
        }
                
        logger.info( "Closing AMQ-Reciever" );
    }

}