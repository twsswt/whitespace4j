package uk.ac.glasgow.senotes.whitespace.flow;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Map;
import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class FlowSpaceState extends BasicState {
		
	private Map<Long,Long> labels;
	
	private  Stack<Long> subRoutines;
	
	public FlowSpaceState(
		Program program, Character[] chars, Map<Long,Long> labels, Stack<Long> subRoutines) {
		
		super(program, chars);
		this.labels = labels;
		this.subRoutines = subRoutines;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		Long label = interpretUnsignedNumber();

		if (getFiniteStateMachine().isInScanMode()) return;
		
		Long position = labels.get(label);
		
		logger.debug("Doing unconditional jump to: "+position+" : from position: "+program.getCounter()+".");
		
		program.jump(position);
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		Long label = interpretUnsignedNumber();
		
		if (!getFiniteStateMachine().isInScanMode()) return;

		logger.debug("Found label ["+label+"] at position ["+program.getCounter()+"].");
		labels.put(label,program.getCounter());
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		Long label = interpretUnsignedNumber();
		
		if (getFiniteStateMachine().isInScanMode()) return;

		Long position = labels.get(label);

		logger.debug(
				"Calling sub-routine ["+label+"] at position ["+position+"] "
				+ "from position ["+program.getCounter()+"] with instruction ["+currentIns+"].");

		subRoutines.push(program.getCounter());
		program.jump(position);
	}

}
