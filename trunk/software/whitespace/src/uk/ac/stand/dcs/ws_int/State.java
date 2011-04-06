package uk.ac.stand.dcs.ws_int;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public abstract class State {
	
	public static final Logger logger = Logger.getLogger(State.class);
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	protected char ta;
	protected char sp;
	protected char lf; 
	
	protected Program p;
	protected String name;
	protected boolean scan_mode;
	
	public static String currentIns = "";
	
	public State (Program p, boolean scan_mode, char[] chars,String name){
		this.p = p;
		this.scan_mode = scan_mode;
		this.name = name;
		ta = chars[0];
		sp = chars[1];
		lf = chars[2];
	}
	
	public void execute () throws InterpretWSException{
		char ins = this.getNextProgramToken();
		currentIns+=ins;
		//logger.debug(currentIns);
		
		if (ins==sp){
			 doActionSP();
		}else if (ins==ta){
			doActionTA();
		}else if (ins==lf){
			doActionLF();
		} else throw new InterpretWSException(p,name);			
	}
	
	protected long interpretSignedNumber() throws InterpretWSException{
		return interpretNumber(true);
	}
	
	protected long interpretUnsignedNumber() throws InterpretWSException{
		return interpretNumber(false);
	}	
	
	protected long interpretNumber(boolean signed) throws InterpretWSException{
		currentIns+=":";
		long result = 0;
		int sign_mult = 1;
		
		if (signed){
			char sign = getNextProgramToken();
			currentIns+=sign;
			if (sign==ta) sign_mult = -1;
			else if (sign==sp) sign_mult = 1;
			else throw new InterpretWSException(p,"number(sign)");		
		}
		
		char c = getNextProgramToken();
		while(c != lf){
			currentIns+=c;
			result *=2;
			if (c==ta) result++;
			else if (c==sp);
			else throw new InterpretWSException(p,"number(value)");
			c = getNextProgramToken();
		}
		return result*sign_mult;
	}
	
	public String getName() {
		return name;
	}
	
	private char getNextProgramToken(){
		char ins = p.getNextToken();
		
		while(!(ins==ta || ins==lf || ins==sp))
			ins = p.getNextToken();
		return ins;
	}
	
	protected abstract void doActionSP() throws InterpretWSException;
	
	protected abstract void doActionTA() throws InterpretWSException;
	
	protected abstract void doActionLF() throws InterpretWSException;
}
