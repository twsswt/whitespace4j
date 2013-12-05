package uk.ac.stand.dcs.ws_int.arithmetic;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

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
