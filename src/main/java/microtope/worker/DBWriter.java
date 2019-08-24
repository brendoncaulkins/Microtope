package microtope.worker;

import java.sql.SQLException;

import microtope.messages.CoinMessage;
import microtope.messages.LoginMessage;
import microtope.messages.LogoutMessage;
import microtope.messages.StepMessage;

public interface DBWriter {

	void writeSteps(StepMessage msg);

	void writePlayer(int player_id, int team_id) throws SQLException;

	void writeCoins(CoinMessage msg);
	
	void writeLogin(LoginMessage msg);
	
	void writeLogout(LogoutMessage msg);

}