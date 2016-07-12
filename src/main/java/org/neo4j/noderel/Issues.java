package org.neo4j.noderel;
import org.neo4j.ogm.annotation.*;

import java.util.Collection;
import java.util.List;
@NodeEntity
public class Issues {
	@GraphId Long id;
	private String issue;
	@Relationship(type = "APPLIES_TO",direction = Relationship.OUTGOING)
	private List<Cost> cost;
	
	@Relationship(type = "APPLIES_TO",direction = Relationship.OUTGOING )
	private List<Reliability> reliability;
	
	@Relationship(type = "APPLIES_TO",direction = Relationship.OUTGOING )
	private List<Timeliness> timeliness;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public Collection<Cost> getCost(){
		return cost;
	}
	public Collection<Reliability> getReliability(){
		return reliability;
	}
	public Collection<Timeliness> getTimeliness(){
		return timeliness;
	}
	public void setCost(List<Cost> cost){
		this.cost = cost;
	}
	public void setReliability(List<Reliability> reliability){
		this.reliability = reliability;
	}
	public void setTimeliness(List<Timeliness> timeliness){
		this.timeliness = timeliness;
	}
}
