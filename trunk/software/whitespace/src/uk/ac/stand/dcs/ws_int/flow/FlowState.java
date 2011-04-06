package uk.ac.stand.dcs.ws_int.flow;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class FlowState extends BasicState {

	public static String NAME = "Flow";
	
	private State flow_lf;
	private State flow_sp;
	private State flow_ta;
	
	public FlowState(Program p, boolean scan_mode, char[] chars, State flow_lf, State flow_sp, State flow_ta) {
		super(p, scan_mode, chars, NAME);	
		this.flow_lf = flow_lf;
		this.flow_sp = flow_sp;
		this.flow_ta = flow_ta;

	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		//logger.debug(name+": doing LF action.");
		flow_lf.execute();
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		//logger.debug(name+": doing SP action.");
		flow_sp.execute();
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		//logger.debug(name+": doing TA action.");
		flow_ta.execute();
	}

}
