package uk.ac.stand.dcs.ws_int.stack;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class StackTabState extends BasicState{

	public static String NAME = "Stack_TA";
	
	private Stack<Long> stack;
	
	public StackTabState(Program program, Character[] chars, Stack<Long> stack) {
		super(program, chars);
		this.stack = stack;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		Long n = interpretSignedNumber();

		if(getFiniteStateMachine().isInScanMode()) return;
		logger.debug("Sliding "+n+" elements of stack "+stack+".");

		Long e1 = stack.pop();
		
		while (n > 0){
			stack.pop();
			n--;
		}
		stack.push(e1);
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		Long index = interpretSignedNumber();
		
		if (getFiniteStateMachine().isInScanMode()) return;
		
		logger.debug("Copying ["+index+"th] element of stack "+stack+".");

		Long n = new Long(stack.elementAt(index.intValue()));
		stack.push(n);	
	}
}
