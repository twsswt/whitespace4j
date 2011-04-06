package uk.ac.stand.dcs.ws_int.stack;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Stack_LF_State extends BasicState {

	public static String NAME = "Stack_LF";
	
	private Stack<Long> stack;
	
	public Stack_LF_State(Program p, boolean scan_mode, char[] chars, Stack<Long> stack) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		logger.debug(name+": Popping the top of the stack.");
		if (scan_mode) return;
		stack.pop();
		logger.debug(name+":"+stack);
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		logger.debug(name+": Copy top stack element.");
		if (scan_mode) return;
		logger.debug(name+":"+stack);
		stack.push(new Long(stack.peek()));
		logger.debug(name+":"+stack);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		logger.debug(name+": Switching top stack elements.");
		if (scan_mode) return;
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		stack.push(e1);
		stack.push(e2);	
	}
}
