package uk.ac.stand.dcs.ws_int.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class OutputState extends BasicState {

	public static String NAME = "IO_SP";
	
	private PrintStream ps;

	private Stack<Long> stack;
	
	public OutputState(Program p, boolean scan_mode, char[] chars, OutputStream os, Stack<Long> stack) {
		super(p, scan_mode, chars, NAME);
		if (scan_mode) return;
		this.ps = new PrintStream(os);
		this.stack = stack;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		if (scan_mode) return;
		logger.debug(name+":Outputing \'"+stack.peek().intValue()+"\'");
		ps.write((char)stack.pop().intValue());
		ps.flush();

	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		if (scan_mode) return;
		logger.debug(name+":"+stack);
		Long value = stack.pop();
		logger.debug(name+":Outputing \'"+value+"\'");
		ps.print(value);
		ps.flush();
	}

}
