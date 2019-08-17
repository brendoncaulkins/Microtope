package microtope.messages;

import java.util.Date;

public class CoinMessage implements PlayerRelatedMessage,AMQMessage {
	
	private final int player;
	private final int coins;
	private Date timestamp =new Date(0) ;
	
	public CoinMessage (int player, int coins){
		this.player=player;
		this.coins=coins;
	}

	public int getPlayer() {
		return player;
	}

	public int getCoins() {
		return coins;
	}

	public void setTimeStamp(Date date) {
		this.timestamp=date;
	}

	public Date getTimeStamp() {
		return timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CoinMessage)) return false;
		var otherParsed = (CoinMessage) o;
		
		return otherParsed.getPlayer() == getPlayer() 
				&& otherParsed.getCoins() == getCoins();
	}
}
