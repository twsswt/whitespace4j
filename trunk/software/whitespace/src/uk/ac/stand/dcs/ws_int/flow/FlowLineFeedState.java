package uk.ac.stand.dcs.ws_int.flow;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;
import uk.ac.stand.dcs.ws_int.Interpreter;
import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class FlowLineFeedState extends BasicState {
	
	private Interpreter interpreter;
	
	public FlowLineFeedState(Program program, Character[] chars, Interpreter interpreter) {
		super(program, chars);
		this.interpreter = interpreter;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		
		logger.debug("Halting at position ["+program.getCounter()+"].");
		interpreter.halt();
	}

}
