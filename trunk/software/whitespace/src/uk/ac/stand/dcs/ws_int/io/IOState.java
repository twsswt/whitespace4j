package uk.ac.stand.dcs.ws_int.io;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class IOState extends BasicState {

	public static String NAME = "IO";
	
	private State input;
	private State output;
	
	public IOState(Program p, boolean scan_mode, char[] chars, State input, State output) {
		super(p, scan_mode, chars, NAME);
		this.input = input;
		this.output = output;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		logger.debug(name+":"+"Output");
		output.execute();
		
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		logger.debug(name+":"+"Input");
		input.execute();
	}

}
