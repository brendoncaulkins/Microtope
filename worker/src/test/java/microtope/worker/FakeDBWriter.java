package microtope.worker;

import java.sql.SQLException;

import microtope.messages.AmqMessage;
import microtope.messages.CoinMessage;
import microtope.messages.LoginMessage;
import microtope.messages.LogoutMessage;
import microtope.messages.StepMessage;

public class FakeDBWriter implements DatabaseWriter {
	
	public AmqMessage lastMessage = null;
	
	public boolean wroteSteps,wrotePlayer,wroteCoins,wroteLogin,wroteLogout;
	
	public boolean throwsSQLException=false;
	public boolean isTouched=false;
	
	public void writeSteps(StepMessage msg) {
		isTouched=true;
		lastMessage=msg;
		wroteSteps=true;
	}

	public void writePlayer(int player_id, int team_id) throws SQLException {
		isTouched=true;
		if(throwsSQLException)
			throw new SQLException();
		else
			wrotePlayer=true;
	}

	public void writeCoins(CoinMessage msg) {
		isTouched=true;
		lastMessage=msg;
		wroteCoins=true;
	}

	public void writeLogin(LoginMessage msg) {
		isTouched=true;
		lastMessage=msg;
		wroteLogin=true;
	}

	public void writeLogout(LogoutMessage msg) {
		isTouched=true;
		lastMessage=msg;
		wroteLogout=true;
	}

}
