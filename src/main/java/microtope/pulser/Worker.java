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
	Team team;
	MessageSender sender;
	
	int numberOfMessages=0;
	
	private int TIMEOUT = 100;
	private double COINQUOTA=0.1; //Every 10th Event will be Coin
	
	public boolean finished = false;
	
	public Worker(int player, Team team, MessageSender sender, int numberOfMessages){
		
		this.player=player;
		this.team=team;
		this.sender=sender;
		this.numberOfMessages=numberOfMessages;
		
		logger.debug("Worker created with Team " + team.toString() + " and player " + player );
	}
	
	public void work() throws JMSException, InterruptedException {
		logger.debug("Worker starts work with " + numberOfMessages + " messages to send" );
		sender.sendMessage(MessageGenerator.createLoginMessage(player));
		for(int i=0;i<numberOfMessages;i++) {
			Thread.sleep(TIMEOUT);
			if(Math.random()<COINQUOTA)
				sender.sendMessage(MessageGenerator.createCoinMessage(player, team, DataGenerator.getRandomCoins()));
			else
				sender.sendMessage(MessageGenerator.createStepMessage(player, DataGenerator.getRandomSteps()));
		}
		logger.debug("Worker send his messages" );
		sender.sendMessage(MessageGenerator.createLogoutMessage(player));
		finished = true;
		logger.trace("Worker finished" );
	}
	
	
	
	public static Worker randomWorker(MessageSender sender, int messages){
		Team t = DataGenerator.getRandomTeam();
		int player = DataGenerator.getRandomPlayerNumber(t);
		Worker w = new Worker (player,t, sender, messages);
		return w;
	}
	
	
}
