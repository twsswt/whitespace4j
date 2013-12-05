package uk.ac.glasgow.senotes.whitespace.stack;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class StackState extends BasicState{
	
	private Stack<Long> stack;
	
	private State stack_ta;
	private State stack_lf;
	
	public StackState(Program p, Character[] chars, Stack<Long> stack, State stack_lf, State stack_ta) {
		super(p, chars);
		this.stack = stack;
		this.stack_ta = stack_ta;
		this.stack_lf = stack_lf;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		stack_lf.execute();
		
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		Long i = interpretSignedNumber();
		
		if(getFiniteStateMachine().isInScanMode())return;
		
		logger.debug("Adding number ["+i+"] to stack "+ stack+"." );
		
		stack.push(i);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		stack_ta.execute();
	}
	
}
