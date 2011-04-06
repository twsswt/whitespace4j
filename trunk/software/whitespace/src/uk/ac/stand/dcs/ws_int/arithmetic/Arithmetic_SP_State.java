package uk.ac.stand.dcs.ws_int.arithmetic;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class Arithmetic_SP_State extends BasicState{

	public static String NAME = "Arithmetic_SP";
	
	private Stack<Long> stack;
	
	private static final char addition = '+';
	private static final char subtraction = '-';
	private static final char multiplication = '*';
	
	public Arithmetic_SP_State(Program p, boolean scan_mode, char[] chars, Stack<Long> stack) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
	}

	@Override
	protected void doActionLF() throws InterpretWSException {
		if (scan_mode) return;
		doOperation(multiplication);
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		if (scan_mode) return;
		doOperation(addition);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		if (scan_mode) return;
		 doOperation(subtraction);
	}
	
	private void doOperation(char op) throws InterpretWSException{
		logger.debug(name+":"+":stack aritmetic operation"+op+":"+stack);
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		switch (op) {
			case multiplication: stack.push(e2*e1);break;
			case addition:  stack.push(e2+e1);break;
			case subtraction:  stack.push(e2-e1);break;
			default: throw new InterpretWSException(p,NAME);
		}
	}

}
