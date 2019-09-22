package microtope.pulser;

import microtope.config.ActiveMqConfiguration;

public class AMQHelpers {

	public static ActiveMqConfiguration validConf() {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		return ActiveMqConfiguration.createActiveMqConfigFromArgs(testArgs);
	}
}
