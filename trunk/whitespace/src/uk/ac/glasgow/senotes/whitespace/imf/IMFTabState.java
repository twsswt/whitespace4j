package uk.ac.glasgow.senotes.whitespace.imf;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class IMFTabState extends State {

	private State arithmeticState;
	private State ioState;
	private State heapState;
		
	public IMFTabState(
		WhiteSpaceProgram program,
		CharacterSet characterSet,
		State arithmeticState,
		State ioState,
		State heapState) {
		
		super(program ,characterSet);
		this.arithmeticState = arithmeticState;
		this.ioState = ioState;
		this.heapState = heapState;
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		logger.debug("Instruction modification: IO");
		ioState.execute();

	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		logger.debug("Instruction modification: Arithmetic");
		arithmeticState.execute();

	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		logger.debug("Instruction modification: Heap");
		heapState.execute();
	}

}
