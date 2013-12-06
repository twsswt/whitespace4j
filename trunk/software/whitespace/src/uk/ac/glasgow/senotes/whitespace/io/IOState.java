package uk.ac.glasgow.senotes.whitespace.io;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class IOState extends State {
	
	private State input;
	private State output;
	
	public IOState(WhiteSpaceProgram program, CharacterSet characterSet, State input, State output) {
		super(program, characterSet);
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
