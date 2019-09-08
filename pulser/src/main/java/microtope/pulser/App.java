package microtope.pulser;

import java.io.IOException;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.config.ActiveMQConfig;

public class App 
{
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args ) throws JMSException, IOException, InterruptedException
    {
        logger.info( "Starting AMQ-Sender" );
        logger.debug( "Recieved " + args + " as arguments" );
        ActiveMQConfig amqconf= ActiveMQConfig.emptyConfig();
        
        amqconf= ActiveMQConfig.createActiveMQConfigFromArgs(args);
        
        AMQMessageSender sender = new AMQMessageSender(amqconf);
        
        logger.info( "Creating Worker...");
        Worker w = Worker.randomWorker(sender, 50);
        
        sender.open(sender.createConnectionFromConfig());
        
        w.work();
        
        while(!w.finished){}
        
        sender.close();
    }
}
