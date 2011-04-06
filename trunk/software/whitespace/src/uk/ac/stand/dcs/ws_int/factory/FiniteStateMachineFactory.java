package uk.ac.stand.dcs.ws_int.factory;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.Interpreter;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.arithmetic.ArithmeticState;
import uk.ac.stand.dcs.ws_int.arithmetic.Arithmetic_SP_State;
import uk.ac.stand.dcs.ws_int.arithmetic.Arithmetic_TA_State;
import uk.ac.stand.dcs.ws_int.flow.FlowState;
import uk.ac.stand.dcs.ws_int.flow.Flow_LF_State;
import uk.ac.stand.dcs.ws_int.flow.Flow_SP_State;
import uk.ac.stand.dcs.ws_int.flow.Flow_TA_State;
import uk.ac.stand.dcs.ws_int.flow.LabelStore;
import uk.ac.stand.dcs.ws_int.heap.Heap;
import uk.ac.stand.dcs.ws_int.heap.HeapState;
import uk.ac.stand.dcs.ws_int.imf.IMFState;
import uk.ac.stand.dcs.ws_int.imf.IMF_TA_State;
import uk.ac.stand.dcs.ws_int.io.IOState;
import uk.ac.stand.dcs.ws_int.io.InputState;
import uk.ac.stand.dcs.ws_int.io.OutputState;
import uk.ac.stand.dcs.ws_int.stack.StackState;
import uk.ac.stand.dcs.ws_int.stack.Stack_LF_State;
import uk.ac.stand.dcs.ws_int.stack.Stack_TA_State;

public class FiniteStateMachineFactory {

	public static State getFiniteStateMachine(
			Program p,
			Stack<Long> stack,
			Heap heap,
			InputStream is,
			OutputStream os,
			Interpreter i,
			LabelStore store,
			char[] chars,
			boolean scan_mode){
		
		State stack_s = new StackState(p, scan_mode, chars, stack,
				new Stack_LF_State(p, scan_mode, chars, stack), 
				new Stack_TA_State(p, scan_mode, chars, stack));
		
		State io_s = new IOState(p, scan_mode, chars, 
				new InputState(p, scan_mode, chars, is, stack, heap),
				new OutputState(p, scan_mode, chars, os, stack)); 
		
		State flow_s = new FlowState(p, scan_mode, chars, 
				new Flow_LF_State(p, scan_mode, chars, i),
				new Flow_SP_State(p, scan_mode, chars, store),
				new Flow_TA_State(p, scan_mode, chars, stack)
				);
		
		State arithmetic_s = getArithmetricState(p, scan_mode, chars, stack);
		
		State heap_s = new HeapState(p, scan_mode, chars, stack, heap);
		
		State imf_s_ta = new IMF_TA_State(p,
				scan_mode, chars,
				arithmetic_s,
				io_s,
				heap_s);
		
		State imf_s = new IMFState(p, scan_mode, chars, stack_s, flow_s, imf_s_ta);
		
		return imf_s;
	}
	
	public static State getArithmetricState(Program p, boolean scan_mode,char[] chars, Stack<Long> stack){
		return  new ArithmeticState(p, scan_mode, chars, 
				getArithmetic_SP_State(p, scan_mode, chars, stack),
				getArithmetic_TA_State(p, scan_mode, chars, stack));
	}
	
	public static State getArithmetic_SP_State(Program p, boolean scan_mode,char[] chars, Stack<Long> stack){
		return new Arithmetic_SP_State(p, scan_mode, chars, stack);
	}
	
	public static State getArithmetic_TA_State(Program p, boolean scan_mode,char[] chars, Stack<Long> stack){
		return new Arithmetic_TA_State(p, scan_mode, chars, stack);
	}	
}
