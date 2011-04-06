package uk.ac.stand.dcs.ws_int.arithmetic;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class ArithmeticState extends BasicState {

	public static String NAME = "ARITHMETIC";
	
	private State arithmetic_sp;
	private State arithmetic_ta;
	
	public ArithmeticState(Program p, boolean scan_mode, char[] chars,State arithmetic_sp, State arithmetic_ta) {
		super(p, scan_mode, chars, NAME);
		this.arithmetic_sp = arithmetic_sp;
		this.arithmetic_ta = arithmetic_ta;
	}

	@Override
	protected void doActionSP() throws InterpretWSException {
		arithmetic_sp.execute();
	}

	@Override
	protected void doActionTA() throws InterpretWSException {
		arithmetic_ta.execute();
	}

}
