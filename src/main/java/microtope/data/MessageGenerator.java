package microtope.data;

public abstract class MessageGenerator {
	
	public static String createLoginMessage(int playerNumber) {
		return String.format("E: Player %d logged in", playerNumber);
	}
	
	public static String createLogoutMessage(int playerNumber) {	
		return String.format("E: Player %d logged out", playerNumber);
	}
	
	public static String createCoinMessage(int playerNumber, Team team, int amount) {
		return String.format("E: Player %d collected %d coins for Team %s", playerNumber, amount, team.toString());
	}
	
	public static String createStepMessage(int playerNumber, int steps) {
		return String.format("M: Player %d moved %d steps", playerNumber, steps);
	}
}
