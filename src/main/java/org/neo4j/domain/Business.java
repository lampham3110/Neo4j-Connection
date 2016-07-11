package org.neo4j.domain;
public class Business{
	private Long id;;
	private String issue;
	private float cost;
	private float reliability;
	private float timeliness;
	
	public Business(final String issue, final float cost, final float reliability, final float timeliness){
		this.issue = issue;
		this.reliability = reliability;
		this.timeliness = timeliness;
	}
	public Business() {
	}
	public void setIssues(String issue){
		this.issue = issue;
	}
	public void setReliability(Float reliability){
		this.reliability = reliability;
	}
	public void setTimeliness(Float timeliness){
		this.timeliness = timeliness;
	}
	
	public String toString(){
		return "Business [Issue=" + issue + ",Cost=" + cost + ",Reliability=" + reliability + ",Timeliness=" + timeliness;
	}
}
	
