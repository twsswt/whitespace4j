package uk.ac.stand.dcs.ws_int.io;

import static uk.ac.stand.dcs.ws_int.FiniteStateMachine.getFiniteStateMachine;

import java.io.IOException;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.FiniteStateMachine;
import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.comment.BasicState;
import uk.ac.stand.dcs.ws_int.heap.Heap;

public class InputState extends BasicState{
	
	private Stack<Long> stack;
	private Heap heap;
		
	public InputState(Program program, Character[] chars, Stack<Long> stack, Heap heap) {
		super(program, chars);
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
			heap.store((long)input, stack.peek());
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
			heap.store(input, stack.peek());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
