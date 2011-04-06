package uk.ac.stand.dcs.ws_int.arithmetic.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.factory.FiniteStateMachineFactory;

public class ArithmetricStateTestCase {

	private State state;
	private Stack<Long> stack;
	
	private char[] chars = new char[]{'T','W','N'};
	private String source = "";
	private Program program;
	
	@Before
	public void setUp() throws Exception {
		
		stack = new Stack<Long>();
		program = new Program("", chars);
		state = FiniteStateMachineFactory.getArithmetricState(program, false, chars, stack);	
	}

	@Test
	public void testAddition(){
		
		program.setSource("WW");
		stack.add(4l);
		stack.add(5l);
		
		try {
			state.execute();
		} catch (InterpretWSException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		assertEquals(9l,stack.peek());
	}

	@Test
	public void testNegativeSubtraction(){
		
		program.setSource("WT");
		stack.add(4l);
		stack.add(5l);
		
		try {
			state.execute();
		} catch (InterpretWSException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		assertEquals(-1l,stack.peek());
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
