package uk.ac.stand.dcs.ws_int.imf;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

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
