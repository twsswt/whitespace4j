package uk.ac.glasgow.senotes.whitespace.heap;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

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
