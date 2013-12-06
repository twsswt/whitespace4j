package uk.ac.glasgow.senotes.whitespace;

import org.apache.log4j.Logger;

public class WhiteSpaceProgram {
	
	protected static final Logger logger = Logger.getLogger(WhiteSpaceProgram.class);
		
	private String source;
	
	private long counter;
	
	public WhiteSpaceProgram (String source){
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
