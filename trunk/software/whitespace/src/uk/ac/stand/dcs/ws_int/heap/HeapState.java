package uk.ac.stand.dcs.ws_int.heap;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class HeapState extends BasicState {
	
	public static String NAME = "HEAP";
	
	private Stack<Long> stack;

	private Heap heap;

	public HeapState(Program p, boolean scan_mode, char[] chars, Stack<Long> stack, Heap heap) {
		super(p, scan_mode, chars, NAME);
		this.stack = stack;
		this.heap = heap;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		if (scan_mode)return;
		long value = stack.pop();
		long key = stack.pop();
		logger.debug("storing "+value+" at "+key+".");
		heap.store(value,key);
		logger.debug(name+":heap:"+heap);
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		if (scan_mode)return;
		long key = stack.pop();
		logger.debug("retrieving value at "+key+".");
		logger.debug(name+":"+heap);
		stack.push(heap.retrieve(key));
	}
}
