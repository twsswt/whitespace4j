package uk.ac.stand.dcs.ws_int.imf;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

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
