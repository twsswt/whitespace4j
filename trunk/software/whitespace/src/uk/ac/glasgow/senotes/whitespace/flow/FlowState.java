package uk.ac.glasgow.senotes.whitespace.flow;

import uk.ac.glasgow.senotes.whitespace.CharacterSet;
import uk.ac.glasgow.senotes.whitespace.InterpretWSException;
import uk.ac.glasgow.senotes.whitespace.WhiteSpaceProgram;
import uk.ac.glasgow.senotes.whitespace.State;

public class FlowState extends State {
	
	private State flowLineFeed;
	private State flowSpace;
	private State flowTab;
	
	public FlowState(WhiteSpaceProgram program, CharacterSet characterSet, State flowLineFeed, State flowSpace, State flowTab) {
		super(program, characterSet);	
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
