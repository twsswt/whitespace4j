package uk.ac.stand.dcs.ws_int.io;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class IOState extends BasicState {
	
	private State input;
	private State output;
	
	public IOState(Program program, Character[] chars, State input, State output) {
		super(program, chars);
		this.input = input;
		this.output = output;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		output.execute();
		
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		input.execute();
	}
	
}
