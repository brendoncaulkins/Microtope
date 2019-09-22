package microtope.data;

public abstract class DataGenerator {
	
	
	private static final int BASESTEPS = 10;
	private static final int MAXSTEPS = 50;
	
	private static final int BASECOINS = 1;
	private static final int MAXCOINS = 3;

	public static Team getRandomTeam() {
		var teams = Team.values();
		var rndIndex = makeNoise(teams.length);
		
		return teams[rndIndex];
	}
	
	public static int getRandomSteps() {
		var noise = makeNoise(MAXSTEPS - BASESTEPS);
		return BASESTEPS + noise;
	}
	
	public static int getRandomCoins() {
		var noise = makeNoise(MAXCOINS - BASECOINS);
		return BASECOINS + noise;
	}
	
	public static int getRandomPlayerNumber() {
		return makeNoise(99999);
	}
	
	private static int makeNoise(int range) {
		return (int)(Math.random() * range);
	}
}
