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
		blogger.setLevel(Level.INFO);
	}
	
	public BasicState(Program p, boolean scan_mode, char[] chars, String name) {
		super(p, scan_mode,chars, name);
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		throw new InterpretWSException(p,getName());
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		throw new InterpretWSException(p,getName());	
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		throw new InterpretWSException(p,getName());
	}
}
