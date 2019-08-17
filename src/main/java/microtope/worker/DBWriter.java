package microtope.worker;

import java.sql.SQLException;

import microtope.messages.CoinMessage;
import microtope.messages.StepMessage;

public interface DBWriter {

	void writeSteps(StepMessage msg);

	void writePlayer(int player) throws SQLException;

	void writeCoins(CoinMessage msg);

}