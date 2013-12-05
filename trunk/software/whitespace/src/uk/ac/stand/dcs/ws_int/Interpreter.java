package uk.ac.stand.dcs.ws_int;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Interpreter   {
	
	protected static final Logger logger = Logger.getLogger(Interpreter.class);
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	/** Program to be intrepreted */
	private Program program;
		
	private Boolean halt = false;
	
	public Interpreter (String source, Character[] chars){
		this.program = new Program(source);
		
		
		FiniteStateMachine machine = FiniteStateMachine.getFiniteStateMachine();
		machine.setInputStream(System.in);
		machine.setOutputStream(System.out);
		machine.initialise(program, this, chars);
		
		machine.setInScanMode(true);
		
		State imfState = machine.getImfState(); 
		
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
		
		while(!halt)
			try {
				logger.debug("Beginning new instruction at position. "+program.getCounter());
				imfState.execute();
			} catch (InterpretWSException e) {
				logger.fatal("Interpretation error at position "+program.getCounter()+".", e);
			}		
	}
	
	/** 
	 * @see uk.ac.stand.dcs.ws_int.Interpreter#halt()
	 */
	public void halt(){
		halt = true;
	}
}
