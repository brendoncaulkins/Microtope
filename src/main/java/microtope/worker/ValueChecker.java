package microtope.worker;


public abstract class ValueChecker {

	public static boolean goodURL(String url_to_check) {
		if(goodIPv4(url_to_check))
			return true;
		if(isValidDomain(url_to_check))
			return true;
		
		return false;

	}
	
	public static boolean isValidDomain(String addStr)
	{
		boolean ret = true;
		
		if("".equals(addStr) || addStr==null)
		{
			ret = false;
		}else if(addStr.startsWith("-")||addStr.endsWith("-"))
		{
			ret = false;
		}else if(addStr.indexOf(".")==-1)
		{
			ret = false;
		}else			
		{		
			// Split domain into String array.
			String domainEle[] = addStr.split("\\.");
			int size = domainEle.length;
			// Loop in the domain String array.
			for(int i=0;i<size;i++)
			{
				// If one domain part string is empty, then reutrn false.
				String domainEleStr = domainEle[i];
				if("".equals(domainEleStr.trim()))
				{
					return false;
				}
			}
			
			// Get domain char array.
			char[] domainChar = addStr.toCharArray();			
			size = domainChar.length;
			// Loop in the char array.
			for(int i=0;i<size;i++)
			{
				// Get each char in the array.
				char eleChar = domainChar[i];
				String charStr = String.valueOf(eleChar);
				
				// If char value is not a valid domain character then return false.
				if(!".".equals(charStr) && !"-".equals(charStr) && !charStr.matches("[a-zA-Z]") && !charStr.matches("[0-9]"))
				{
					ret = false;
					break;
				}				
			}
		}		
		return ret;
	}
	
	
	public static boolean goodIPv4(String ip_to_check) {
		try {
	        if ( ip_to_check == null || ip_to_check.isEmpty() )
	            return false;
	
	        String[] parts = ip_to_check.split( "\\." );
	        
	        if ( parts.length != 4 )
	            return false;
	
	        for ( String s : parts ) {
	            int i = Integer.parseInt( s );
	            if ( (i < 0) || (i > 255) )
	                return false;
	        }
	        if ( ip_to_check.endsWith(".") )
	            return false;
	
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	public static boolean goodPort(String port_to_check) {
		if(port_to_check == null || port_to_check.isEmpty())
			return false;
		else {
			try {
				int p = Integer.parseInt(port_to_check);
				if(p>0)
					return true;
				else
					return false;
			}
			catch(Exception e) {
				return false;
			}
		}
	}
	
}
