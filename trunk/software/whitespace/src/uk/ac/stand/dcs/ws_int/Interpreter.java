package uk.ac.stand.dcs.ws_int;

import java.util.Stack;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import uk.ac.stand.dcs.ws_int.factory.FiniteStateMachineFactory;
import uk.ac.stand.dcs.ws_int.heap.Heap;

public class Interpreter   {
	
	protected static final Logger logger = Logger.getLogger(Interpreter.class);
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	/** Program to be intrepreted */
	private Program p;
	
	/** Shared runtime stack */
	private Stack<Long> stack = new Stack<Long>();
	
	/** Shared heap */
	private Heap heap = new Heap();
	
	private State imf_s;
	
	private boolean halt = false;
	
	public Interpreter (String program, char[] chars){
		p = new Program(program,chars);
		
		imf_s = FiniteStateMachineFactory.getFiniteStateMachine(p, stack, heap,System.in, System.out, this, null, chars, false);
		
		while(!halt)
			try {
				logger.debug("Beginning new instruction at position. "+p.getCounter());
				imf_s.execute();
			} catch (InterpretWSException e) {
				logger.fatal("Interpretation error at position "+p.getCounter()+".", e);
			}		
	}
	
	/** 
	 * @see uk.ac.stand.dcs.ws_int.Interpreter#halt()
	 */
	public void halt(){
		halt = true;
	}
}
