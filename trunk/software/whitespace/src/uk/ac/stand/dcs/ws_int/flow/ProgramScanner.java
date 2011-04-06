package uk.ac.stand.dcs.ws_int.flow;

import java.util.HashMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import uk.ac.stand.dcs.ws_int.InterpretWSException;
import uk.ac.stand.dcs.ws_int.Program;
import uk.ac.stand.dcs.ws_int.State;
import uk.ac.stand.dcs.ws_int.factory.FiniteStateMachineFactory;

public class ProgramScanner implements LabelStore{

	protected static final Logger logger = Logger.getLogger(ProgramScanner.class);
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	private String program;
	
	private HashMap<Long,Long> labels = new HashMap<Long,Long>();
	
	private char[] chars;
	
	public ProgramScanner(String program, char[] chars){
		this.program = program;
		this.chars = chars;
	}
	
	public HashMap<Long,Long> getLabels() {
		Program sim = new Program(program,chars);
		labels = new HashMap<Long,Long>();
		logger.debug("Scanner"+"Beginning new scan.");
		State labelFSM = FiniteStateMachineFactory.getFiniteStateMachine(sim, null, null, null, null, null,this, chars, true);
		while(sim.getCounter()<program.length())
			try {
					logger.debug("Scanner"+"Beginning new instruction at position. "+sim.getCounter());
				labelFSM.execute();
				
				
			} catch (InterpretWSException e) {
				logger.fatal("Interpretation error at position "+sim.getCounter()+".", e);
			}
		logger.debug("Scanner"+"Finished scan.");
		return labels;
	}

	public void addLabel(Long label, Long pos) throws InterpretWSException{
		if (labels.get(label)!=null) throw new InterpretWSException(null, "Scanner:"+label+":"+State.currentIns);
		logger.debug("Found label: "+label+" : at position: "+pos+". "+State.currentIns);
		labels.put(label,pos);
	}
}
