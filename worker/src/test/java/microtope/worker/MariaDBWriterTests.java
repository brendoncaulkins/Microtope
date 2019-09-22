package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import microtope.config.SqlConfig;
import microtope.messages.CoinMessage;
import microtope.messages.LoginMessage;
import microtope.messages.LogoutMessage;
import microtope.messages.StepMessage;

class MariaDBWriterTests {

	@Test
	void testConstructor_emptySQLConfig_ShouldThrowIllegalArgumentException() {
		SqlConfig conf = SqlConfig.emptyConfig();
		assertThrows(IllegalArgumentException.class, () -> {
			new MariaDBWriter(conf);
		});
		
	}

	@Test
	void testConstructor_validSQLConfig_butIsOffline_ShouldbeBuild() {
		var test = new MariaDBWriter(validSQLConf());

		return;
	}
	
	@Test
	void testOpen_validSQLConfig_butIsOffline_ShouldThrowSQLException() {
		var test = new MariaDBWriter(validSQLConf());
		
		assertThrows(SQLException.class, () -> {
			test.open(test.buildConnectionFromConfig());
		});
	}

	@Test
	void testBuildConnectionFromConf_validSQLConfig_butIsOffline_ShouldThrowSQLException() {
		var test = new MariaDBWriter(validSQLConf());
		
		assertThrows(SQLException.class, () -> {
			test.buildConnectionFromConfig();
		});
	}
	
	@Test
	void testWriteSteps_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		
		var msg = new StepMessage(1000,10);
		
		test.writeSteps(msg);
	}
	@Test
	void testWriteCoins_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		
		var msg = new CoinMessage(1000,10);
		
		test.writeCoins(msg);
	}
	@Test
	void testWriteLogin_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		
		var msg = new LoginMessage(1000,10);
		
		test.writeLogin(msg);
	}
	
	@Test
	void testWritePlayer_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		
		test.writePlayer(10,10);
	}
	
	@Test
	void testWriteLogout_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		
		var msg = new LogoutMessage(1000);
		
		test.writeLogout(msg);
	}
	
	@Test
	void testWriteLogout_withFailingConnection_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		var con = new FakeConnection();
		
		con.throwsSQLException=true;
		try {
			test.open(con);
			var msg = new LogoutMessage(1000);
			test.writeLogout(msg);
			return;
		} catch (SQLException e) {

			fail();
		}
	}
	
	@Test
	void testClose_wasNeverOpen_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		try {
			test.close();
			return;
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	void testOpen_withFakeSQLCon_WhichThrowsSQLException_shouldFailWithoutError() {
		var test = new MariaDBWriter(validSQLConf());
		var con = new FakeConnection();
		
		con.throwsSQLException=true;
		
		try {
			test.open(con);
			return;
		} catch (SQLException e) {

			fail();
		}
	}
	
	private SqlConfig validSQLConf() {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		
		SqlConfig conf = SqlConfig.createSqlConfigFromArgs(testArgs);
		return conf;
	}
}
