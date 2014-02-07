package uk.ac.glasgow.senotes.whitespace.flow;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Map;
import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;

public class FlowTabState extends State{
	
	private Stack<Long> stack;

	private Map<Long, Long> labels;
	
	private Stack<Long> subRoutines;
	
	public FlowTabState(
		WhiteSpaceProgram program,
		CharacterSet characterSet,
		Stack<Long> stack,
		Map<Long,Long> labels,
		Stack<Long> subRoutines) {
		
		super(program, characterSet);
		
		this.stack = stack;
		this.labels = labels;
		this.subRoutines = subRoutines;
	}
	
	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;		
		
		Long position = subRoutines.pop();
		
		logger.debug(
			"Returning from end of sub-routine at position "
			+ "["+program.getCounter()+"] to position ["+position+"].");
		
		program.jump(position);
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		Long label = interpretUnsignedNumber();
		
		if (getFiniteStateMachine().isInScanMode()) return;
				
		Long condition = stack.pop();
		Long position = labels.get(label);
		
		logger.debug("Doing conditional  0=["+condition+"] jump to ["+label+"] referencing position ["+position+"].");
		
		if (condition==0) program.jump(position);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		Long label = interpretUnsignedNumber();
		
		if (getFiniteStateMachine().isInScanMode()) return;
		
		Long position = labels.get(label);
		Long condition = stack.pop();
		
		logger.debug("Doing conditional ["+condition+"]<0 jump to ["+label+"] referencing position ["+position+"].");
		
		if (condition<0) program.jump(position);
	}
}
