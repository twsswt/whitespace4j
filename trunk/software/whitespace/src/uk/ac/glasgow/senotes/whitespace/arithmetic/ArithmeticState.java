package uk.ac.glasgow.senotes.whitespace.arithmetic;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class ArithmeticState extends BasicState {

	public static String NAME = "ARITHMETIC";
	
	private State arithmeticSpace;
	private State arithmeticTab;
	
	public ArithmeticState(Program program, Character[] chars, State arithmeticSpace, State arithmeticTab) {
		super(program, chars);
		this.arithmeticSpace = arithmeticSpace;
		this.arithmeticTab = arithmeticTab;
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		arithmeticSpace.execute();
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		arithmeticTab.execute();
	}

}
