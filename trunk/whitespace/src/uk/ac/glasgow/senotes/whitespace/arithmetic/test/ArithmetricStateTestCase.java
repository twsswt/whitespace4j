package uk.ac.glasgow.senotes.whitespace.arithmetic.test;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.factory.StateFactory;

@RunWith(Parameterized.class)
public class ArithmetricStateTestCase {
	
	@BeforeClass
	public static void setUpClass(){
		PropertyConfigurator.configure("log4j.properties");
	}

	private State state;
	private Stack<Long> stack;
	
	private CharacterSet characterSet = CharacterSet.VISIBLE;
	private WhiteSpaceProgram program;
	
	@Parameters
	public static Collection<Object[]> paths() {
	
		Object[][] testData = {
				{"WW",new Long[]{4l, 5l}, new Long[]{9l}}, // addition
				{"WT",new Long[]{4l, 5l}, new Long[]{-1l}} // subtraction

		};
	
		return Arrays.asList(testData);
			
	};
	
	private String programSource;
	private Long[] stackStartValues;
	private Long[] stackEndValues;
	
	public ArithmetricStateTestCase(
		String programSource, Long[] stackStartValues, Long[] stackEndValues){
		this.programSource = programSource;
		this.stackStartValues = stackStartValues;
		this.stackEndValues = stackEndValues;
	}

	
	@Before
	public void setUp() throws Exception {
		
		stack = new Stack<Long>();
		program = new WhiteSpaceProgram(programSource);
		state = StateFactory.createArithmetricState(program, characterSet, stack);
		
		for (Long value: stackStartValues) stack.add(value);
	}

	@Test
	public void testArithmeticState(){
		while (!program.isAtEnd()) 		
			try {
				state.execute();
			} catch (InterpretWSException e) {
				fail();
				e.printStackTrace();
			}
		
		for (Long expectedOutput : stackEndValues){
			Long actualOutput = stack.pop(); 
			assertEquals(expectedOutput, actualOutput);
		}
	}

}
