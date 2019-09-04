package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import microtope.config.SQLConfig;

class MariaDBWriterTests {

	@Test
	void testConstructor_emptySQLConfig_ShouldThrowIllegalArgumentException() {
		SQLConfig conf = SQLConfig.emptyConfig();
		assertThrows(IllegalArgumentException.class, () -> {
			new MariaDBWriter(conf);
		});
		
	}
	
	@Test
	void testConstructor_validSQLConfig_butIsOffline_ShouldThrowSQLException() {

		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		
		SQLConfig conf = SQLConfig.createSQLConfigFromArgs(testArgs);
		
		assertThrows(SQLException.class, () -> {
			new MariaDBWriter(conf);
		});
		}
}
