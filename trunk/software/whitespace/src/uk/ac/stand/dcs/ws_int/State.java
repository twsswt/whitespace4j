package uk.ac.stand.dcs.ws_int;

import org.apache.log4j.Logger;

public abstract class State {
	
	public static final Logger logger = Logger.getLogger(State.class);
	
	protected Character tab;
	protected Character space;
	protected Character lineFeed; 
	
	protected Program program;
	
	public static String currentIns = "";
	
	public State (Program program, Character[] chars){
		this.program = program;
		tab = chars[0];
		space = chars[1];
		lineFeed = chars[2];
	}
	
	public void execute () throws InterpretWSException{
		Character ins = this.getNextProgramToken();
		currentIns+=ins;
		
		Boolean scanMode = FiniteStateMachine.getFiniteStateMachine().isInScanMode();
		
		if (ins==space) doSpaceAction();
		else if (ins==tab) doTabAction();
		else if (ins==lineFeed) doLineFeedAction();
		else if (ins==null && scanMode) return;
		else
			throw new InterpretWSException(program, this);			
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
			if (sign==tab) sign_mult = -1;
			else if (sign==space) sign_mult = 1;
			else throw new InterpretWSException(program, this);		
		}
		
		Character c = getNextProgramToken();
		while(c != lineFeed){
			currentIns+=c;
			result *=2;
			if (c==tab) result++;
			else if (c==space);
			else throw new InterpretWSException(program, this);
			c = getNextProgramToken();
		}
		return result*sign_mult;
	}
	
	private Character getNextProgramToken(){
		Character ins = program.getNextToken();
		
		while(!(ins==tab || ins==lineFeed || ins==space || ins==null))
			ins = program.getNextToken();
		
		return ins;
	}
	
	protected abstract void doSpaceAction() throws InterpretWSException;
	
	protected abstract void doTabAction() throws InterpretWSException;
	
	protected abstract void doLineFeedAction() throws InterpretWSException;
}
