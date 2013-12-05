package uk.ac.glasgow.senotes.whitespace.io;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.FiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class OutputState extends BasicState {
	
	private Stack<Long> stack;
	
	public OutputState(Program program, Character[] chars, Stack<Long> stack) {
		super(program, chars);
		this.stack = stack;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
				getFiniteStateMachine();
		
		if (machine.isInScanMode()) return;
		
		logger.debug("Outputing value ["+stack.peek().intValue()+"] on top of stack "+stack+".");
		
		machine.getPrintWriter().write((char)stack.pop().intValue());
		machine.getPrintWriter().flush();

	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
				getFiniteStateMachine();
		
		if (machine.isInScanMode()) return;

		Long value = stack.pop();

		logger.debug(":Outputing value ["+value+"] popped from stack "+stack+".");
		
		machine.getPrintWriter().print(value);
		machine.getPrintWriter().flush();
	}

}
