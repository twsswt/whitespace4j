package uk.ac.stand.dcs.ws_int.flow;

import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.comment.BasicState;

public class FlowState extends BasicState {
	
	private State flowLineFeed;
	private State flowSpace;
	private State flowTab;
	
	public FlowState(Program program, Character[] chars, State flowLineFeed, State flowSpace, State flowTab) {
		super(program, chars);	
		this.flowLineFeed = flowLineFeed;
		this.flowSpace = flowSpace;
		this.flowTab = flowTab;

	}

	@Override
	protected void doLineFeedAction() throws InterpretWSException {
		//logger.debug(name+": doing LF action.");
		flowLineFeed.execute();
	}

	@Override
	protected void doSpaceAction() throws InterpretWSException {
		//logger.debug(name+": doing SP action.");
		flowSpace.execute();
	}

	@Override
	protected void doTabAction() throws InterpretWSException {
		//logger.debug(name+": doing TA action.");
		flowTab.execute();
	}

}
