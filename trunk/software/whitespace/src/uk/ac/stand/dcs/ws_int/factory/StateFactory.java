package uk.ac.stand.dcs.ws_int.factory;

import java.util.Stack;

import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.arithmetic.ArithmeticSpaceState;
import uk.ac.stand.dcs.ws_int.arithmetic.ArithmeticState;
import uk.ac.stand.dcs.ws_int.arithmetic.ArithmeticTabState;

public class StateFactory {

	public static State createArithmeticSpaceState(Program p, Character[] chars, Stack<Long> stack){
		return new ArithmeticSpaceState(p, chars, stack);
	}

	public static State createArithmeticTabState(Program p,Character[] chars, Stack<Long> stack){
		return new ArithmeticTabState(p, chars, stack);
	}

	public static State createArithmetricState(Program program, Character[] chars, Stack<Long> stack){
		return  new ArithmeticState(program, chars, 
				createArithmeticSpaceState(program, chars, stack),
				createArithmeticTabState(program, chars, stack));
	}

}
