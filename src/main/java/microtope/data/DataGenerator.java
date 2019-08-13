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
	
	/*
	 * returns a number between 10 and 50
	 */
	public static int getRandomSteps() {
		var noise = makeNoise(MAXSTEPS-BASESTEPS);
		return BASESTEPS+noise;
	}
	
	/*
	 * returns a number between 1 and 3
	 */
	public static int getRandomCoins() {
		var noise = makeNoise(MAXCOINS-BASECOINS);
		return BASECOINS+noise;
	}
	
	/*
	 * PlayerNumbers are encoded:
	 * Red Players start with 1XXXXX
	 * Blue Players start with 2XXXXX 
	 * etc
	 */
	public static int getRandomPlayerNumber(Team t) {
		int randomPart = makeNoise(9999);
		
		int prefix = 0;
		switch(t) {
			case RED: prefix = 1; break;
			case BLUE: prefix = 2; break;
			case PURPLE: prefix = 3; break;
			case BLACK: prefix = 4; break;
			default: prefix = 0; break;
		}
		
		return randomPart+10000*prefix;
	}
	
	private static int makeNoise(int range) {
		return (int)(Math.random()*range);
	};
}
