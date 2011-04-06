package uk.ac.stand.dcs.ws_int.imf;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class IMFState extends BasicState {
	
	private State stack_s;
	private State flow_s;
	private State imf_ta;
	
	public static String NAME = "IMF";

	public IMFState(Program p, boolean scan_mode, char[] chars, State stack_s, State flow_s, State imf_ta){
		super(p, scan_mode, chars, NAME);
		this.stack_s = stack_s;
		this.flow_s = flow_s;
		this.imf_ta = imf_ta;
	}

	public void execute() throws InterpretWSException{
		currentIns = "";
		//logger.debug("reseting instruction");
		super.execute();
	}
	
	@Override
	protected void doActionLF() throws InterpretWSException{
		logger.debug("Instruction modification: Flow");
		flow_s.execute();
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		logger.debug("Instruction modification: Stack");
		stack_s.execute();
	}

	@Override
	protected void doActionTA() throws InterpretWSException{
		imf_ta.execute();

	}

}
