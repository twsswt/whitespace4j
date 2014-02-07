package uk.ac.glasgow.senotes.whitespace.imf;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.FiniteStateMachine;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class IMFState extends State {
	
	private State stackState;
	private State flowState;
	private State imfTab;
	
	public IMFState(
		WhiteSpaceProgram program,
		CharacterSet characterSet,
		State stackState,
		State flowState,
		State imfTab){
		
		super(program, characterSet);
		this.stackState = stackState;
		this.flowState = flowState;
		this.imfTab = imfTab;
	}

	public void execute() throws InterpretWSException{
		FiniteStateMachine finiteStateMachine =
			FiniteStateMachine.getFiniteStateMachine();

		finiteStateMachine.resetCurrentInstruction();
		super.execute();
	}
	
	@Override
	protected void doLineFeedAction() throws InterpretWSException{
		logger.debug("Instruction modification: Flow");
		flowState.execute();
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		logger.debug("Instruction modification: Stack");
		stackState.execute();
	}

	@Override
	protected void doTabAction() throws InterpretWSException{
		imfTab.execute();
	}

}
