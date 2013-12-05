package uk.ac.glasgow.senotes.whitespace.io;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

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
