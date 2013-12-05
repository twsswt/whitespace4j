package uk.ac.stand.dcs.ws_int.heap;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class HeapState extends BasicState {
		
	private Stack<Long> stack;

	private Heap heap;

	public HeapState(Program program, Character[] chars, Stack<Long> stack, Heap heap) {
		super(program, chars);
		this.stack = stack;
		this.heap = heap;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
				
		if (getFiniteStateMachine().isInScanMode())return;
		
		Long value = stack.pop();
		Long key = stack.pop();

		logger.debug("sSoring ["+value+"] at ["+key+"]. on heap "+heap+".");
		
		heap.store(value,key);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		if (getFiniteStateMachine().isInScanMode())return;
		
		Long key = stack.pop();
		
		logger.debug("retrieving value at key ["+key+"] from heap ["+heap+"].");
		
		stack.push(heap.retrieve(key));
	}
}
