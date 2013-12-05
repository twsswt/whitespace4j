package uk.ac.glasgow.senotes.whitespace;

public class InterpretWSException extends Exception {
	/****/
	private static final long serialVersionUID = -3813716494184795541L;

	public InterpretWSException(Program p, State state){
		super(
				"Unexpected character ("+(p!=null?p.getCurrentToken():"")+
				"), at "+(p!=null?p.getCounter():"")+
				", during execution of "+state+
				".");
	}
}
