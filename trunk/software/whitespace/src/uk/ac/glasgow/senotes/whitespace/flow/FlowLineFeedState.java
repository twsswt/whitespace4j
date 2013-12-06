package uk.ac.glasgow.senotes.whitespace.flow;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Interpreter;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;

public class FlowLineFeedState extends State {
	
	private Interpreter interpreter;
	
	public FlowLineFeedState(WhiteSpaceProgram program, CharacterSet characterSet, Interpreter interpreter) {
		super(program, characterSet);
		this.interpreter = interpreter;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		
		logger.debug("Halting at position ["+program.getCounter()+"].");
		interpreter.halt();
	}
}
