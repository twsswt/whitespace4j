package uk.ac.glasgow.senotes.whitespace.factory;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticSpaceState;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticState;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticTabState;

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
