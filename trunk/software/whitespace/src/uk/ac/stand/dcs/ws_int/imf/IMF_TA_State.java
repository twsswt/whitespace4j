package uk.ac.stand.dcs.ws_int.imf;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class IMF_TA_State extends BasicState {

	private State arithmetic_s;
	private State io_s;
	private State heap_s;
	
	public static String NAME = "IMF_TA";
	
	public IMF_TA_State(Program p, boolean scan_mode, char[] chars, State arithmetic_s, State io_s, State heap_s) {
		super(p, scan_mode,chars, NAME);
		this.arithmetic_s = arithmetic_s;
		this.io_s = io_s;
		this.heap_s = heap_s;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		logger.debug("Instruction modification: IO");
		io_s.execute();

	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		logger.debug("Instruction modification: Arithmetic");
		arithmetic_s.execute();

	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		logger.debug("Instruction modification: Heap");
		heap_s.execute();
	}

}
