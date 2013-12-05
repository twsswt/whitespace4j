package uk.ac.glasgow.senotes.whitespace.arithmetic;

import static uk.ac.glasgow.senotes.whitespace.FiniteStateMachine.getFiniteStateMachine;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class ArithmeticTabState extends BasicState{

	private static final char division = '/';
	private static final char modulo = '%';
	
	private Stack<Long> stack;
	
	public ArithmeticTabState(Program program, Character[] chars, Stack<Long> stack) {
		super(program, chars);
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
			case division: stack.push(e2/e1);
			case modulo:  stack.push(e2%e1);
			default: throw new InterpretWSException(program, this);
		}
	}

}