package org.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

public class Timeliness extends Business{
	private List<Timeliness> time = new ArrayList<>();
	public List<Timeliness> getTimeliness(){
		return new ArrayList<Timeliness>(this.time);
	}
}
