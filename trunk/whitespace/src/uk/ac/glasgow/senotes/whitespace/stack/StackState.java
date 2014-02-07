package uk.ac.glasgow.senotes.whitespace.stack;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import org.apache.log4j.Logger;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class StackState extends State{
	
	private static final Logger logger = Logger.getLogger(StackState.class);
	
	private Stack<Long> stack;
	
	private State stack_ta;
	private State stack_lf;
	
	public StackState(WhiteSpaceProgram p, CharacterSet characterSet, Stack<Long> stack, State stack_lf, State stack_ta) {
		super(p, characterSet);
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
