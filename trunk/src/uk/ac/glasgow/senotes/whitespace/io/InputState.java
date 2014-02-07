package uk.ac.glasgow.senotes.whitespace.io;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.io.IOException;
import java.util.Map;
import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.FiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class InputState extends State{
	
	private Stack<Long> stack;
	private Map<Long,Long> heap;
		
	public InputState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack, Map<Long,Long> heap) {
		super(program, characterSet);
		this.stack = stack;
		this.heap = heap;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
			getFiniteStateMachine();
		
		try {
			if(machine.isInScanMode())return;
			char input = (char)machine.getBufferedReader().read();
			heap.put((long)input, stack.peek());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		
		FiniteStateMachine machine = 
				getFiniteStateMachine();
		
		try {
			if(machine.isInScanMode())return;
			Long input = Long.parseLong(machine.getBufferedReader().readLine());
			heap.put(input, stack.peek());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}