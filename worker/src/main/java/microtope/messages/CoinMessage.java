package microtope.messages;

public class CoinMessage extends AMQBaseMessage implements PlayerRelatedMessage {
	
	private final int player;
	private final int coins;
	
	public CoinMessage (int player, int coins){
		this.player=player;
		this.coins=coins;
	}

	public int getPlayer_Id() {
		return player;
	}

	public int getCoins() {
		return coins;
	}

	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CoinMessage)) return false;
		var otherParsed = (CoinMessage) o;
		
		return otherParsed.getPlayer_Id() == getPlayer_Id() 
				&& otherParsed.getCoins() == getCoins();
	}
}
