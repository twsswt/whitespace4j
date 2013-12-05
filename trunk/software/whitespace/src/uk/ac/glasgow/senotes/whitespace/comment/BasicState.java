package uk.ac.glasgow.senotes.whitespace.comment;

import org.apache.log4j.Logger;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;

public class BasicState extends State {

	protected static final Logger blogger = Logger.getLogger(BasicState.class);
	
	public BasicState(Program program, Character[] chars) {
		super(program,chars);
	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);	
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);
	}
}
