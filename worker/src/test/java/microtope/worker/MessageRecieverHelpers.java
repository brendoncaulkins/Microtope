package microtope.worker;

import microtope.config.ActiveMQConfig;

public class MessageRecieverHelpers {

	public static final ActiveMQConfig validConf () {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		return ActiveMQConfig.createActiveMQConfigFromArgs(testArgs);
	}
	
}
