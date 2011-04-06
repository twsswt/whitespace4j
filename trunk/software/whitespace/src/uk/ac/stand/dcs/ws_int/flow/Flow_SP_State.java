package uk.ac.stand.dcs.ws_int.flow;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Flow_SP_State extends BasicState {
	
	public static String NAME = "Flow_SP";
	
	private LabelStore store;
	
	public Flow_SP_State(Program p, boolean scan_mode, char[] chars, LabelStore store) {
		super(p, scan_mode, chars, NAME);
		this.store = store;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		Long i = interpretUnsignedNumber();
		logger.debug("Doing unconditional jump to: "+i+" : from position: "+p.getCounter()+".");
		if (scan_mode) return;
		p.jump(i);
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		Long i = interpretUnsignedNumber();
		logger.debug("Found label: "+i+" : at position: "+p.getCounter()+".");
		if (!scan_mode) return;
		store.addLabel(i,p.getCounter());
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		logger.debug("Calling sub-routine: at position "+p.getCounter()+" with instruction: "+currentIns);
		Long i = interpretUnsignedNumber();
		logger.debug("Found label: "+i+". at position "+p.getCounter()+" with instruction: "+currentIns);
		if (scan_mode) return;
		p.callSubRoutine(i);
	}

}
