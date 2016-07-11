package org.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

public final class Cost extends Business {
	
	private List<Cost> costs = new ArrayList<>();
	
	public List<Cost> getCost(){
		return new ArrayList<Cost>(this.costs);
	}
}
