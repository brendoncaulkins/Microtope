package microtope.pulser;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import microtope.data.DataGenerator;
import microtope.data.MessageGenerator;
import microtope.data.Team;

public class Worker {
	
	private static Logger logger = LogManager.getLogger(Worker.class);

	
	int player;
	int team_id;
	MessageSender sender;
	
	int numberOfMessages=0;
	
	private int TIMEOUT = 100;
	private double COINQUOTA=0.1; //Every 10th Event will be Coin
	
	public boolean finished = false;
	
	public Worker(int player, int team_id, MessageSender sender, int numberOfMessages){
		
		this.player=player;
		this.sender=sender;
		this.numberOfMessages=numberOfMessages;
		this.team_id=team_id;
		
		logger.debug("Worker created with Team " + team_id + " and player " + player );
	}
	
	public void work() throws JMSException, InterruptedException {
		logger.debug("Worker starts work with " + numberOfMessages + " messages to send" );
		sender.sendMessage(MessageGenerator.createLoginMessage(player,team_id));
		for(int i=0;i<numberOfMessages;i++) {
			Thread.sleep(TIMEOUT);
			sendRandomizedMessage();
		}
		logger.debug("Worker send his messages" );
		sender.sendMessage(MessageGenerator.createLogoutMessage(player));
		finished = true;
		logger.trace("Worker finished" );
	}
	
	private void sendRandomizedMessage() {
		try {
			String message;
			if(Math.random()<COINQUOTA)
				message = MessageGenerator.createCoinMessage(player, team_id, DataGenerator.getRandomCoins());
			else
				message = MessageGenerator.createStepMessage(player, DataGenerator.getRandomSteps());
			sender.sendMessage(message);
		} catch (JMSException e) {
			logger.error(e);
		}
	}
	
	public static Worker randomWorker(MessageSender sender, int messages){
		Team t = DataGenerator.getRandomTeam();
		int player = DataGenerator.getRandomPlayerNumber();
		Worker w = new Worker (player,t.ordinal(), sender, messages);
		return w;
	}
	
	
}
