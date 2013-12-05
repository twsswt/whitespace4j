package uk.ac.stand.dcs.ws_int.stack;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

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
