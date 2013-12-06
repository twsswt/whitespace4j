package uk.ac.glasgow.senotes.whitespace;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class Interpreter   {
	
	protected static final Logger logger = Logger.getLogger(Interpreter.class);
			
	private Boolean halt = false;
	
	private State imfState;
	
	private WhiteSpaceProgram program;
	
	public Interpreter (WhiteSpaceProgram program, CharacterSet characterSet, InputStream in, OutputStream out){
		this.program = program;		
		
		FiniteStateMachine machine = FiniteStateMachine.getFiniteStateMachine();
		machine.setInputStream(in);
		machine.setOutputStream(out);
		machine.initialise(program, this, characterSet);
		
		imfState = machine.getImfState(); 
		
		machine.setInScanMode(true);		
				
		logger.debug("Beginning scan of labels.");
		
		while (!program.isAtEnd()){
			try {
				imfState.execute();
			} catch (InterpretWSException e) {
				logger.fatal("Interpretation error at position "+ program.getCounter() +".", e);
			}
		}
		
		logger.debug("Completed scan of labels");

		machine.setInScanMode(false);
		program.reset();		
	}
	
	public void run (){
		while(!halt)
			try {
				logger.debug("Beginning new instruction at position ["+program.getCounter()+"].");
				imfState.execute();
			} catch (InterpretWSException e) {
				logger.fatal("Interpretation error at position "+program.getCounter()+".", e);
			}
	}
	
	public void halt(){
		halt = true;
	}
}
