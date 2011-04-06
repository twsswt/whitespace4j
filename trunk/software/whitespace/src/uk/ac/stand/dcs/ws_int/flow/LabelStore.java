package uk.ac.stand.dcs.ws_int.flow;

import java.util.HashMap;

import uk.ac.stand.dcs.ws_int.InterpretWSException;

public interface LabelStore {
	public HashMap<Long,Long> getLabels();
	
	public void addLabel(Long label, Long pos) throws InterpretWSException;
}
