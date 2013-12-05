package uk.ac.stand.dcs.ws_int;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import uk.ac.stand.dcs.ws_int.factory.StateFactory;
import uk.ac.stand.dcs.ws_int.flow.FlowState;
import uk.ac.stand.dcs.ws_int.flow.FlowLineFeedState;
import uk.ac.stand.dcs.ws_int.flow.FlowSpaceState;
import uk.ac.stand.dcs.ws_int.flow.FlowTabState;
import uk.ac.stand.dcs.ws_int.heap.Heap;
import uk.ac.stand.dcs.ws_int.heap.HeapState;
import uk.ac.stand.dcs.ws_int.imf.IMFState;
import uk.ac.stand.dcs.ws_int.imf.IMFTabState;
import uk.ac.stand.dcs.ws_int.io.IOState;
import uk.ac.stand.dcs.ws_int.io.InputState;
import uk.ac.stand.dcs.ws_int.io.OutputState;
import uk.ac.stand.dcs.ws_int.stack.StackState;
import uk.ac.stand.dcs.ws_int.stack.StackLineFeedState;
import uk.ac.stand.dcs.ws_int.stack.StackTabState;

public class FiniteStateMachine {	
	
	private boolean inScanMode;
	
	private State imfState;
	
	private BufferedReader bufferedReader;
	
	private PrintWriter printWriter;

	
	private FiniteStateMachine(){}
	
	public void initialise(
		Program program,
		Interpreter interpreter,
		Character[] chars){
		
		Heap heap = new Heap();
		Stack<Long> stack = new Stack<Long>();
		Map<Long,Long> labels = new HashMap<Long,Long>();		
		Stack<Long> subRoutines = new Stack<Long>();
		
		State stackState = new StackState(program, chars, stack,
				new StackLineFeedState(program, chars, stack), 
				new StackTabState(program, chars, stack));
		
		State ioState = new IOState(program, chars, 
				new InputState(program, chars, stack, heap),
				new OutputState(program, chars, stack)); 
		
		State flowState = new FlowState(program, chars, 
				new FlowLineFeedState(program, chars, interpreter),
				new FlowSpaceState(program, chars, labels, subRoutines),
				new FlowTabState(program, chars, stack, labels, subRoutines)
				);
		
		State arithmeticState = StateFactory.createArithmetricState(program, chars, stack);
		
		State heapState = new HeapState(program, chars, stack, heap);
		
		State imfStateTab = new IMFTabState(program,
				chars,
				arithmeticState,
				ioState,
				heapState);
		
		imfState = new IMFState(program, chars, stackState, flowState, imfStateTab);	
	}
	
	
	public boolean isInScanMode() {
		return inScanMode;
	}

	public void setInScanMode(boolean scanMode) {
		this.inScanMode = scanMode;
	}
	
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setInputStream(InputStream is) {
		if (is!=null)bufferedReader = new BufferedReader(new InputStreamReader(is));
	}

	public PrintWriter getPrintWriter() {
		return printWriter;
	}

	public void setOutputStream(OutputStream os) {
		this.printWriter = new PrintWriter(os);
	}

	public State getImfState() {
		return imfState;
	}

	
	/* Singleton pattern */
	
	private static FiniteStateMachine finiteStateMachine;
		
	public static FiniteStateMachine getFiniteStateMachine() {
		if (finiteStateMachine == null)
			finiteStateMachine =
			new FiniteStateMachine();

		return finiteStateMachine;
	}
}
