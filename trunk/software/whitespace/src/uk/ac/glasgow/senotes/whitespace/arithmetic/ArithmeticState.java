package uk.ac.glasgow.senotes.whitespace.arithmetic;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class ArithmeticState extends State {

	public static String NAME = "ARITHMETIC";
	
	private State arithmeticSpace;
	private State arithmeticTab;
	
	public ArithmeticState(WhiteSpaceProgram program, CharacterSet characterSet, State arithmeticSpace, State arithmeticTab) {
		super(program, characterSet);
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
