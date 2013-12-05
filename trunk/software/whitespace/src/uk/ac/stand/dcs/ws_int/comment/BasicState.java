package uk.ac.stand.dcs.ws_int.comment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;

public class BasicState extends State {

	protected static final Logger blogger = Logger.getLogger(BasicState.class);
	{
		//initialise logging.
		PropertyConfigurator.configure("log4j.properties");
		blogger.setLevel(Level.DEBUG);
	}
	
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
