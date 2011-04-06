package uk.ac.stand.dcs.ws_int;

import java.util.Stack;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import uk.ac.stand.dcs.ws_int.flow.ProgramScanner;

public class Program {
	
	protected static final Logger logger = Logger.getLogger(Program.class);
	{
		//initialise logging.
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	/** stack of return points for sub routine calls */
	private Stack<Long> s_routines = new Stack<Long>();
	
	private String source;
	
	private long counter;

	private char[] chars;
	
	public Program (String source, char[] chars){
		this.source = source;
		this.chars = chars;
	}
	
	public long getCounter(){
		return counter;
	}
	
	public void callSubRoutine(Long label){
		this.s_routines.push(counter);
		jump(label);
	}
	
	public void jump(Long label){
		ProgramScanner ps = new ProgramScanner(source, chars);
		logger.debug("Jump request to label: "+label+" at position "+counter+".");
		counter = ps.getLabels().get(label);
		logger.debug("Jumping to position: "+counter);
	}
	
	public void returnSubRoutine(){
		Long i = s_routines.pop();
		logger.debug("Program:Returning from sub-routine to position: "+i+".");
		counter = i;
	}
	
	public char getNextToken (){
		return source.charAt((int)counter++);
	}
	
	public char getCurrentToken(){
		return source.charAt((int)counter);
	}
	
	public void decrementCounter(){
		counter--;
	}

	public void setSource(String source) {
		this.source = source;
		
	}
}
