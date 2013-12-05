package uk.ac.glasgow.senotes.whitespace.imf;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

public class IMFState extends BasicState {
	
	private State stackState;
	private State flowState;
	private State imfTab;
	
	public IMFState(Program program, Character[] chars, State stackState, State flowState, State imfTab){
		super(program, chars);
		this.stackState = stackState;
		this.flowState = flowState;
		this.imfTab = imfTab;
	}

	public void execute() throws InterpretWSException{
		currentIns = "";
		//logger.debug("reseting instruction");
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
