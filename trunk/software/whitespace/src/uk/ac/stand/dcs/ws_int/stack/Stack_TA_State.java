package uk.ac.stand.dcs.ws_int.stack;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Stack_TA_State extends BasicState{

	public static String NAME = "Stack_TA";
	
	private Stack<Long> stack;
	
	public Stack_TA_State(Program p, boolean scan_mode, char[] chars, Stack<Long> stack) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		long n = interpretSignedNumber();
		logger.debug(name+": sliding "+n+" element of stack.");
		if(scan_mode) return;
		Long e1 = stack.pop();
		while (n > 0){
			stack.pop();n--;
		}
		stack.push(e1);
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		Long i = interpretSignedNumber();
		logger.debug(name+": Copying "+i+"th element of stack.");
		if (scan_mode) return;
		Long n = new Long(stack.elementAt(i.intValue()));
		stack.push(n);	
	}
}
