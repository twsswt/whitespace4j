package uk.ac.glasgow.senotes.whitespace.imf;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class IMFTabState extends BasicState {

	private State arithmeticState;
	private State ioState;
	private State heapState;
		
	public IMFTabState(
		Program program,
		Character[] chars,
		State arithmeticState,
		State ioState,
		State heapState) {
		
		super(program ,chars);
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
