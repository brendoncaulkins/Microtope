package microtope.pulser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import microtope.pulser.ValueChecker;

class ValueCheckerTests {
	
	@Test
	public void testURLChecker_kubernetesDNS_shouldBeTrue() {
		//This is extra important for Kubernetes Service Discovery will look like this
		String url_to_check="my-svc.my-namespace.svc.cluster-domain.example";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testURLChecker_kubernetesDNS2_shouldBeTrue() {
		//This is extra important for Kubernetes Service Discovery will look like this
		String url_to_check="auto-generated-name.my-svc.my-namespace.svc.cluster-domain.example";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testURLChecker_noWWW_shouldBeTrue() {
		String url_to_check="google.com";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertTrue(result);
	}
	
	@Test void testURLChecker_singleName_shouldBeFalse() {
		String url_to_check="ebay";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testURLChecker_validIP4_shouldBeTrue() {
		String url_to_check="127.0.0.1";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertTrue(result);
	}

	@Test
	public void testURLChecker_someGarbage_shouldBeFalse() {
		String url_to_check="Some Garbage @@";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testURLChecker_validDomainName_shouldBeTrue() {
		String url_to_check="www.google.com";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertTrue(result);
	}

	@Test
	public void testURLChecker_validDomainNameWithPath_shouldBeFalse() {
		String url_to_check="www.hosebund.de/hosen";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testURLChecker_validURLWithProtocoll_shouldBeFalse() {
		String url_to_check="https://www.google.de";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testURLChecker_validURLWithPort_shouldBeFalse() {
		String url_to_check="hosenbund.de:61616";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testURLChecker_nullURL_shouldBeFalse() {
		String url_to_check=null;
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testURLChecker_emptyURL_shouldBeFalse() {
		String url_to_check="";
		
		boolean result = ValueChecker.goodURL(url_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_validIP4_shouldBeTrue() {
		String ip_to_check = "172.172.011.211";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testIP4ValueChecker_validShortIP4_shouldBeTrue() {
		String ip_to_check = "11.1.0.2";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testIP4ValueChecker_localhost_shouldBeTrue() {
		String ip_to_check = "127.0.0.1";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testIP4ValueChecker_someOtherString_shouldBeFalse() {
		String ip_to_check = "Bad String";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_tooShortIP_shouldBeFalse() {
		String ip_to_check = "172.172.211";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testIP4ValueChecker_IPEndsWithDot_shouldBeFalse() {
		String ip_to_check = "172.172.211.";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testIP4ValueChecker_IPEndsWithDot2_shouldBeFalse() {
		String ip_to_check = "172.172.211.172.";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_IPstartsWithDot_shouldBeFalse() {
		String ip_to_check = ".172.172.211";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_IPHasTooHighNumber_shouldBeFalse() {
		String ip_to_check = "256.172.172.211";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_emptyIP_shouldBeFalse() {
		String ip_to_check = "";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	

	@Test
	public void testIP4ValueChecker_nullIP_shouldBeFalse() {
		String ip_to_check = null;
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	
	@Test
	public void testIP4ValueChecker_otherDelimiter_shouldBeFalse() {
		String ip_to_check = "172:172:011:211";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testIP4ValueChecker_tooLongIP_shouldBeFalse() {
		String ip_to_check = "172.172.1.5.221";
		
		boolean result = ValueChecker.goodIPv4(ip_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testPortChecker_defaultAMQPort_shouldBeTrue() {
		String port_to_check = "61616";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testPortChecker_shortValidPort_shouldBeTrue() {
		String port_to_check = "85";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertTrue(result);
	}
	
	@Test
	public void testPortChecker_someOtherString_shouldBeFalse() {
		String port_to_check = "Hello";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testPortChecker_zeroPort_shouldBeFalse() {
		String port_to_check = "0";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testPortChecker_negativePort_shouldBeFalse() {
		String port_to_check = "-85";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertFalse(result);
	}
	
	@Test
	public void testPortChecker_nullPort_shouldBeFalse() {
		String port_to_check = null;
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertFalse(result);
	}

	@Test
	public void testPortChecker_emptyPort_shouldBeFalse() {
		String port_to_check = "";
		
		boolean result = ValueChecker.goodPort(port_to_check);
		
		assertFalse(result);
	}
}
