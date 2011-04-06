package uk.ac.stand.dcs.ws_int.flow;

import uk.ac.stand.dcs.ws_int.Interpreter;
import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Flow_LF_State extends BasicState {
	
	public static String NAME = "Flow_LF";

	private Interpreter i;
	
	public Flow_LF_State(Program p, boolean scan_mode, char[] chars, Interpreter i) {
		super(p, scan_mode, chars, NAME);
		this.i = i;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		logger.debug("Halting. at position "+p.getCounter()+".");
		if (scan_mode) return;
		i.halt();
	}

}
