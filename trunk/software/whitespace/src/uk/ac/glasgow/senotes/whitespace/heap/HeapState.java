package uk.ac.glasgow.senotes.whitespace.heap;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Map;
import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class HeapState extends State {
		
	private Stack<Long> stack;

	private Map<Long,Long> heap;

	public HeapState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack, Map<Long,Long> heap) {
		super(program, characterSet);
		this.stack = stack;
		this.heap = heap;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
				
		if (getFiniteStateMachine().isInScanMode())return;
		
		Long value = stack.pop();
		Long key = stack.pop();

		logger.debug("Storing ["+value+"] at ["+key+"]. on heap "+heap+".");
		
		heap.put(key, value);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		if (getFiniteStateMachine().isInScanMode())return;
		
		Long key = stack.pop();
		
		logger.debug("retrieving value at key ["+key+"] from heap ["+heap+"].");
		
		stack.push(heap.get(key));
	}
}
