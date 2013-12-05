package uk.ac.glasgow.senotes.whitespace.flow;

import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.Program;
import uk.ac.glasgow.senotes.whitespace.State;
import uk.ac.glasgow.senotes.whitespace.comment.BasicState;

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
