package uk.ac.glasgow.senotes.whitespace.arithmetic;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;

public class ArithmeticTabState extends State{

	private static final char division = '/';
	private static final char modulo = '%';
	
	private Stack<Long> stack;
	
	public ArithmeticTabState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack) {
		super(program, characterSet);
		this.stack = stack;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		doOperation(division);
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		if (getFiniteStateMachine().isInScanMode()) return;
		doOperation(modulo);
	}
	
	private void doOperation(char op) throws InterpretWSException{
		Long e1 = stack.pop();
		Long e2 = stack.pop();
		
		switch (op) {
			case division:
				stack.push(e2/e1);
				break;
			case modulo: 
				stack.push(e2%e1);
				break;
			default: 
				throw new InterpretWSException(program, this);
		}
	}

}