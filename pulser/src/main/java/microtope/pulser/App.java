package microtope.pulser;

import java.io.IOException;

import javax.jms.JMSException;

import microtope.config.ActiveMqConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	
	private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws JMSException, IOException, InterruptedException {
        logger.info("Starting AMQ-Sender");
        logger.debug("Recieved " + args + " as arguments");
        ActiveMqConfiguration amqconf = ActiveMqConfiguration.emptyConfig();
        
        amqconf = ActiveMqConfiguration.createActiveMqConfigFromArgs(args);
        
        ActiveMqMessageSender sender = new ActiveMqMessageSender(amqconf);
        
        logger.info("Creating Worker...");
        Worker worker = Worker.randomWorker(sender, 50);
        
        sender.open(sender.createConnectionFromConfig());
        
        worker.work();
        
        while (!worker.finished) {
        	
        }
        
        sender.close();
    }
}
