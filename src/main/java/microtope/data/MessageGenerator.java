package microtope.data;

public abstract class MessageGenerator {
	
	public static String createLoginMessage(int player_id,int team_id) {
		return String.format("E: Player %d logged in for team %d", player_id, team_id );
	}
	
	public static String createLogoutMessage(int playerNumber) {	
		return String.format("E: Player %d logged out", playerNumber);
	}
	
	public static String createCoinMessage(int playerNumber, int team_id, int amount) {
		return String.format("E: Player %d collected %d coins for Team %d", playerNumber, amount, team_id);
	}
	
	public static String createStepMessage(int playerNumber, int steps) {
		return String.format("M: Player %d moved %d steps", playerNumber, steps);
	}
}
