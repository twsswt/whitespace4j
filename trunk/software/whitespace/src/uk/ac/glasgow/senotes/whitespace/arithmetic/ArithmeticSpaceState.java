package uk.ac.glasgow.senotes.whitespace.arithmetic;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class ArithmeticSpaceState extends BasicState{
	
	private Stack<Long> stack;
	
	private static final char ADDITION = '+';
	private static final char SUBTRACTION = '-';
	private static final char MULTIPLICATION = '*';
	
	public ArithmeticSpaceState(Program program, Character[] chars, Stack<Long> stack) {
		super(program, chars);
		this.stack = stack;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		doOperation(MULTIPLICATION);
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		doOperation(ADDITION);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		 doOperation(SUBTRACTION);
	}
	
	private void doOperation(char op) throws InterpretWSException{
		logger.debug("Performing stack aritmetic operation "+op+ "on stack "+stack+".");
		
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		
		switch (op) {
			case MULTIPLICATION: stack.push(e2*e1);break;
			case ADDITION:  stack.push(e2+e1);break;
			case SUBTRACTION:  stack.push(e2-e1);break;
			default: throw new InterpretWSException(program, this);
		}
	}

}
