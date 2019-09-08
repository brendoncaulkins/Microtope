package microtope.pulser;

import microtope.config.ActiveMQConfig;

public class AMQHelpers {

	public static ActiveMQConfig validConf() {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		return ActiveMQConfig.createActiveMQConfigFromArgs(testArgs);
	}
}
