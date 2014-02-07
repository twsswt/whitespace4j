package uk.ac.glasgow.senotes.whitespace.factory;

import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticSpaceState;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticState;
import uk.ac.glasgow.senotes.whitespace.arithmetic.ArithmeticTabState;

public class StateFactory {

	public static State createArithmeticSpaceState(WhiteSpaceProgram p, CharacterSet characterSet, Stack<Long> stack){
		return new ArithmeticSpaceState(p, characterSet, stack);
	}

	public static State createArithmeticTabState(WhiteSpaceProgram p, CharacterSet characterSet, Stack<Long> stack){
		return new ArithmeticTabState(p, characterSet, stack);
	}

	public static State createArithmetricState(WhiteSpaceProgram program, CharacterSet characterSet, Stack<Long> stack){
		return  new ArithmeticState(program, characterSet, 
				createArithmeticSpaceState(program, characterSet, stack),
				createArithmeticTabState(program, characterSet, stack));
	}

}
