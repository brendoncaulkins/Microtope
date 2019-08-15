package microtope.messages;

public class CoinMessage implements PlayerRelatedMessage {
	
	private final int player;
	private final int coins;
	
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
	
}
