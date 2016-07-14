package org.neo4j.nodes;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Timeliness {
	@GraphId Long id;
	private float timeliness;
	@Relationship(type = "APPLIES_TO")
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getTimeliness() {
		return timeliness;
	}
	public void setTimeliness(float timeliness) {
		this.timeliness = timeliness;
	}
}