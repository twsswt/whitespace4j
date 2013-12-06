package uk.ac.glasgow.senotes.whitespace;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import uk.ac.glasgow.senotes.whitespace.factory.StateFactory;
import uk.ac.glasgow.senotes.whitespace.flow.FlowLineFeedState;
import uk.ac.glasgow.senotes.whitespace.flow.FlowSpaceState;
import uk.ac.glasgow.senotes.whitespace.flow.FlowState;
import uk.ac.glasgow.senotes.whitespace.flow.FlowTabState;
import uk.ac.glasgow.senotes.whitespace.heap.HeapState;
import uk.ac.glasgow.senotes.whitespace.imf.IMFState;
import uk.ac.glasgow.senotes.whitespace.imf.IMFTabState;
import uk.ac.glasgow.senotes.whitespace.io.IOState;
import uk.ac.glasgow.senotes.whitespace.io.InputState;
import uk.ac.glasgow.senotes.whitespace.io.OutputState;
import uk.ac.glasgow.senotes.whitespace.stack.StackLineFeedState;
import uk.ac.glasgow.senotes.whitespace.stack.StackState;
import uk.ac.glasgow.senotes.whitespace.stack.StackTabState;

public class FiniteStateMachine {	
	
	private boolean inScanMode;
	
	private State imfState;
	
	private String currentInstruction;
	
	private BufferedReader bufferedReader;
	
	private PrintWriter printWriter;
	
	public void initialise(
		WhiteSpaceProgram program,
		Interpreter interpreter,
		CharacterSet characterSet){
		
		Map<Long,Long> heap = new HashMap<Long,Long>();
		Stack<Long> stack = new Stack<Long>();
		Map<Long,Long> labels = new HashMap<Long,Long>();		
		Stack<Long> subRoutines = new Stack<Long>();
		resetCurrentInstruction();
		
		State stackState = new StackState(
			program, 
			characterSet,
			stack,
			new StackLineFeedState(program, characterSet, stack), 
			new StackTabState(program, characterSet, stack)
		);
		
		State ioState = new IOState(program, characterSet, 
				new InputState(program, characterSet, stack, heap),
				new OutputState(program, characterSet, stack)); 
		
		State flowState = new FlowState(program, characterSet, 
				new FlowLineFeedState(program, characterSet, interpreter),
				new FlowSpaceState(program, characterSet, labels, subRoutines),
				new FlowTabState(program, characterSet, stack, labels, subRoutines)
				);
		
		State arithmeticState = StateFactory.createArithmetricState(program, characterSet, stack);
		
		State heapState = new HeapState(program, characterSet, stack, heap);
		
		State imfStateTab = new IMFTabState(program,
				characterSet,
				arithmeticState,
				ioState,
				heapState);
		
		imfState = new IMFState(program, characterSet, stackState, flowState, imfStateTab);	
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
	
	public void addTokenToCurrentInstruction(Character token) {
		this.currentInstruction += token;
	}
	
	public String getCurrentInstruction(){
		return this.currentInstruction;
	}
	
	public void resetCurrentInstruction(){
		currentInstruction = "";
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
