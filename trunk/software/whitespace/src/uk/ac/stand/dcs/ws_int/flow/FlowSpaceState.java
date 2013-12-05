package uk.ac.stand.dcs.ws_int.flow;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.util.Map;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

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
