package uk.ac.glasgow.senotes.whitespace.heap;

import java.util.HashMap;

public class Heap {
	
	private HashMap<Long,Long> map = new HashMap<Long,Long>();
	
	public void store(Long value, Long key){
		map.put(key, value);
	}
	
	public Long retrieve(Long key){
		return map.get(key);
	}
	
	public String toString (){return map.toString();}
}
