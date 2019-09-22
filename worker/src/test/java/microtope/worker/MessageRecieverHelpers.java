package microtope.worker;

import microtope.config.ActiveMqConfiguration;

public class MessageRecieverHelpers {

	public static final ActiveMqConfiguration validConf () {
		String[] testArgs= new String[] {"Adress","1005","Queue","User","Pwd"};
		return ActiveMqConfiguration.createActiveMqConfigFromArgs(testArgs);
	}
	
}
