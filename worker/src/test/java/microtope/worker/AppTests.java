package microtope.worker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTests {

	@Test
	void testMain_tooLowArguments_shouldThrowIllegalArgumentException() {
		String[] testArgs = {"OnlyOne"};
		assertThrows(IllegalArgumentException.class, () -> {App.main(testArgs);});
	}
	
	@Test
	void testMain_tooManyArguments_shouldThrowIllegalArgumentException() {
		String[] testArgs = {"Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much","Too Much"};
		assertThrows(IllegalArgumentException.class, () -> {App.main(testArgs);});
	}

	@Test
	void testTakeNtoM_EmptyArgs_shouldBeEmpty() {
		String[] testArgs= {};
		
		String[] result = App.takeNtoM(testArgs,0, 0);
		
		assertEquals(0,result.length);
	}
	
	@Test
	void testTakeNtoM_Take0To1_shouldBeOneElement() {
		String[] testArgs= {"A","B","C"};
		
		String[] result = App.takeNtoM(testArgs,0, 1);
		
		assertEquals(1,result.length);
	}
	@Test
	void testTakeNtoM_Take0To1_shouldBeFirst() {
		String[] testArgs= {"A","B","C"};
		
		String[] result = App.takeNtoM(testArgs,0, 1);
		
		assertEquals("A",result[0]);
	}
	
	@Test
	void testTakeFirstN_takeFirstOne_shouldHaveSize1() {
		String[] testArgs= {"A","B","C"};
		
		String[] result = App.takeFirstN(testArgs, 1);
		
		assertEquals(1,result.length);
	}
	
	@Test
	void testTakeFirstN_takeMoreThanPossible_shouldThrowArrayIndexOutOfBoundsException() {
		String[] testArgs= {"A","B","C"};
		
		assertThrows(ArrayIndexOutOfBoundsException.class,() -> {
			 App.takeFirstN(testArgs, 10);
		});
	}
	
	@Test
	void testTakeFirstN_takeFirstOne_shouldBeFirstElement() {
		String[] testArgs= {"A","B","C"};
		
		String[] result = App.takeFirstN(testArgs, 1);
		
		assertEquals("A",result[0]);		
	}
	
	
	@Test
	void testTakeNtoM_Take0To2_shouldHaveSize2() {
		String[] testArgs= {"A","B","C"};
		
		String[] result = App.takeNtoM(testArgs,0, 2);
		
		assertEquals(2,result.length);
	}
}
