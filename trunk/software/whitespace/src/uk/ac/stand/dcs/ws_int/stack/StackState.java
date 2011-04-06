package uk.ac.stand.dcs.ws_int.stack;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class StackState extends BasicState{

	public static String NAME = "Stack";
	
	private Stack<Long> stack;
	
	private State stack_ta;
	private State stack_lf;
	
	public StackState(Program p, boolean scan_mode, char[] chars, Stack<Long> stack, State stack_lf, State stack_ta) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
		this.stack_ta = stack_ta;
		this.stack_lf = stack_lf;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		stack_lf.execute();
		
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		Long i = interpretSignedNumber();
		//logger.debug(name+": adding number to stack: "+i+" at position "+p.getCounter());
		if(scan_mode)return;
		stack.push(i);
		logger.debug(name+":"+stack);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		stack_ta.execute();
	}
	
}
