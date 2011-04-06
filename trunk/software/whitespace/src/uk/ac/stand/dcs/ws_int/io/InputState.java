package uk.ac.stand.dcs.ws_int.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;
import uk.ac.stand.dcs.ws_int.heap.Heap;

public class InputState extends BasicState{

	public static String NAME = "IO_TA";
	
	private Stack<Long> stack;
	private Heap heap;
	
	private BufferedReader buf;
	
	public InputState(Program p, boolean scan_mode, char[] chars,InputStream is, Stack<Long> stack, Heap heap) {
		super(p, scan_mode, chars, NAME);
		if (is!=null)buf = new BufferedReader(new InputStreamReader(is));
		this.stack = stack;
		this.heap = heap;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		
		try {
			if(scan_mode)return;
			char input = (char)buf.read();
			heap.store((long)input, stack.peek());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		try {
			if(scan_mode)return;
			long input = Long.parseLong(buf.readLine());
			heap.store(input, stack.peek());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
