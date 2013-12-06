package uk.ac.glasgow.senotes.whitespace.io;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import org.apache.log4j.Logger;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.FiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;

public class OutputState extends State {
	
	private static final Logger logger = Logger.getLogger(OutputState.class);

	
	private Stack<Long> stack;
	
	public OutputState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack) {
		super(program, characterSet);
		this.stack = stack;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
				getFiniteStateMachine();
		
		if (machine.isInScanMode()) return;
		logger.debug("Outputing value ["+stack.peek().intValue()+"] on top of stack "+stack+".");
		
		int value = stack.pop().intValue();
		
		machine.getPrintWriter().write((char)value);
		machine.getPrintWriter().flush();

	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
				getFiniteStateMachine();
		
		if (machine.isInScanMode()) return;

		int value = stack.pop().intValue();
		 

		logger.debug("Outputing value ["+value+"] popped from stack "+stack+".");
		
		machine.getPrintWriter().print(value);
		machine.getPrintWriter().flush();
	}

}
