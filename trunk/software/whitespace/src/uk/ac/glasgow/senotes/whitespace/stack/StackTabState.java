package uk.ac.glasgow.senotes.whitespace.stack;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;

public class StackTabState extends State{
	
	private Stack<Long> stack;
	
	public StackTabState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack) {
		super(program, characterSet);
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
