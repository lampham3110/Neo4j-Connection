package org.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

public class Reliability extends Business{
	private List<Reliability> reliabilities = new ArrayList<>();
	public List<Reliability> getReliability(){
		return new ArrayList<Reliability>(this.reliabilities);
	}
	
}
