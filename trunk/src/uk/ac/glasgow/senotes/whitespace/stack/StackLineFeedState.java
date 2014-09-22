package uk.ac.glasgow.senotes.whitespace.stack;

import java.util.Stack;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class StackLineFeedState extends State {
	
	private Stack<Long> stack;
	
	public StackLineFeedState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack) {
		super(program, characterSet);
		this.stack = stack;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		logger.debug("Popping the top of the stack "+stack+".");
		stack.pop();
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		logger.debug("Copying top stack element "+stack+".");
		stack.push(
				Long.valueOf(stack.peek()));
		logger.debug("Copied top stack element "+stack+".");
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		logger.debug("Switching top stack elements.");
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		stack.push(e1);
		stack.push(e2);	
	}
}
