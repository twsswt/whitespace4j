package uk.ac.glasgow.senotes.whitespace.flow;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Interpreter;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

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
