package uk.ac.stand.dcs.ws_int.stack;

import java.util.Stack;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;
import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class StackLineFeedState extends BasicState {
	
	private Stack<Long> stack;
	
	public StackLineFeedState(Program program, Character[] chars, Stack<Long> stack) {
		super(program, chars);
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
		logger.debug("Copying top stack element"+stack+".");
		stack.push(new Long(stack.peek()));
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
