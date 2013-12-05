package uk.ac.stand.dcs.ws_int.flow;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.util.Map;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class FlowTabState extends BasicState{
	
	private Stack<Long> stack;

	private Map<Long, Long> labels;
	
	private Stack<Long> subRoutines;
	
	public FlowTabState(
		Program program,
		Character[] chars,
		Stack<Long> stack,
		Map<Long,Long> labels,
		Stack<Long> subRoutines) {
		
		super(program, chars);
		
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
