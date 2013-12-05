package uk.ac.glasgow.senotes.whitespace.arithmetic.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.factory.StateFactory;

public class ArithmetricStateTestCase {

	private State state;
	private Stack<Long> stack;
	
	private Character[] chars = new Character[]{'T','W','N'};
	private Program program;
	
	@Before
	public void setUp() throws Exception {
		
		stack = new Stack<Long>();
		program = new Program("");
		state = StateFactory.createArithmetricState(program, chars, stack);	
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
		Assert.assertEquals(9l,stack.peek().longValue());
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
		assertEquals(-1l,stack.peek().longValue());
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
