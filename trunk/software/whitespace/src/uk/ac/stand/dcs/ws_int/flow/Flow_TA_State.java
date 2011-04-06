package uk.ac.stand.dcs.ws_int.flow;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Flow_TA_State extends BasicState{

	public static String NAME = "Flow_TA";
	
	private Stack<Long> stack;
	
	public Flow_TA_State(Program p, boolean scan_mode, char[] chars,Stack<Long> stack) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
	}
	
	@Override
	protected void doActionLF() throws InterpretWSException {
		if (scan_mode) return;
		logger.debug(name+":Returning from sub-routine at position "+p.getCounter()+".");
		logger.debug(name+":"+stack);
		p.returnSubRoutine();
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		Long label = interpretUnsignedNumber();
		if (scan_mode) return;
		logger.debug(name+":Doing conditional jump to "+label+".");
		if (stack.pop()==0) p.jump(label);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		Long label = interpretUnsignedNumber();
		if (scan_mode) return;
		logger.debug(name+":Doing (lt) conditional jump to "+label+".");
		logger.debug(name+":"+stack);
		if (stack.pop()<0) p.jump(label);
	}
}
