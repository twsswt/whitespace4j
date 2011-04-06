package uk.ac.stand.dcs.ws_int.arithmetic;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Arithmetic_TA_State extends BasicState{

	private static final char division = '/';
	private static final char modulo = '%';
	
	public static String NAME = "Arithmetic_TA";
	private Stack<Long> stack;
	
	public Arithmetic_TA_State(Program p, boolean scan_mode, char[] chars, Stack<Long> stack) {
		super(p, scan_mode, chars,NAME);
		this.stack = stack;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		if (scan_mode) return;
		doOperation(division);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		if (scan_mode) return;
		doOperation(modulo);
	}
	
	private void doOperation(char op) throws InterpretWSException{
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		
		switch (op) {
			case division: stack.push(e2/e1);
			case modulo:  stack.push(e2%e1);
			default: throw new InterpretWSException(p,NAME);
		}
	}

}
