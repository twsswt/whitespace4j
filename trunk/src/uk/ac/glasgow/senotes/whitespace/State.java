package uk.ac.glasgow.senotes.whitespace;

import org.apache.log4j.Logger;

public abstract class State {
	
	protected static final Logger logger = Logger.getLogger(State.class);
	
	private Character tab;
	private Character space;
	private Character lineFeed; 
	
	protected WhiteSpaceProgram program;
	
	private FiniteStateMachine finiteStateMachine;
	
	public State (WhiteSpaceProgram program, CharacterSet characterSet){
		this.program = program;
		tab = characterSet.getTab();
		space = characterSet.getSpace();
		lineFeed = characterSet.getLineFeed();
		
		finiteStateMachine =
			FiniteStateMachine.getFiniteStateMachine();
	}
	
	public void execute () throws InterpretWSException{
		Character token = this.getNextProgramToken();
			
		finiteStateMachine.addTokenToCurrentInstruction(token);
		
		Boolean scanMode = 
			finiteStateMachine.isInScanMode();
		
		if (token == null)
			if(scanMode)
				return;
			else 
				throw new InterpretWSException(program, this);			
		
		else if (token.equals(space)) doSpaceAction();
		else if (token.equals(tab)) doTabAction();
		else if (token.equals(lineFeed)) doLineFeedAction();
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
		
		finiteStateMachine.addTokenToCurrentInstruction(':');
		long result = 0;
		int sign_mult = 1;
		
		if (signed){
			Character sign = getNextProgramToken();
			finiteStateMachine.addTokenToCurrentInstruction(sign);

			if (sign == null)
				throw new InterpretWSException(program, this);
			else if (sign.equals(tab))
				sign_mult = -1;
			else if (sign.equals(space))
				sign_mult = 1;
			else 
				throw new InterpretWSException(program, this);		
		}
		
		Character c = getNextProgramToken();
		if (c == null)
			throw new InterpretWSException(program, this);
		
		while(!c.equals(lineFeed)){
			finiteStateMachine.addTokenToCurrentInstruction(c);
			
			result *=2;
			
			if (c.equals(tab))
				result++;
			else if (c.equals(space))
				;//do nothing.
			else
				throw new InterpretWSException(program, this);

			c = getNextProgramToken();
			if (c == null)
				throw new InterpretWSException(program, this);

		}
		return result*sign_mult;
	}
	
	private Character getNextProgramToken(){
		Character token = program.getNextToken();
		
		while(!
			(token == null || 
			 token.equals(tab) || 
			 token.equals(lineFeed) ||
			 token.equals(space))
			 )
			token = program.getNextToken();
		
		return token;
	}
	
	protected void doLineFeedAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);
	}

	protected void doSpaceAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);	
	}

	protected void doTabAction() throws InterpretWSException {
		throw new InterpretWSException(program, this);
	}
}
