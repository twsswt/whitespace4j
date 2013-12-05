package uk.ac.stand.dcs.ws_int;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Program {
	
	protected static final Logger logger = Logger.getLogger(Program.class);
	{
		//initialise logging.
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
		
	private String source;
	
	private long counter;
	
	public Program (String source){
		this.source = source;
	}
	
	public long getCounter(){
		return counter;
	}
	
	public void jump(Long position){
		logger.debug("Jump request to position: "+position+" from position "+ counter +".");
		counter = position;
	}
	
	public Character getNextToken (){
		if (counter < source.length())
			return source.charAt((int)counter++);
		else
			return null;
	}
	
	public Character getCurrentToken(){
		return source.charAt((int)counter);
	}
	
	public void decrementCounter(){
		counter--;
	}

	public void setSource(String source) {
		this.source = source;		
	}
	
	public Boolean isAtEnd(){
		return getCounter() == source.length();
	}
	
	public void reset(){
		counter = 0;
	}
}
