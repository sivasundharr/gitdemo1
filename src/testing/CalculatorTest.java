package testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class CalculatorTest {
	Calculator c = new Calculator();
	
	@Test
	@RepeatedTest(2)
	public void testFact(RepetitionInfo repetitionInfo) {
		System.out.println(repetitionInfo.getCurrentRepetition());
		//assertEquals(120,c.fact(4));
		
		assertEquals(2,c.add(1, 1));
	}

}
