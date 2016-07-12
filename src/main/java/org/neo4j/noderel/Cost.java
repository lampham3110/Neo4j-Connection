package org.neo4j.noderel;
import org.neo4j.ogm.annotation.*;

@NodeEntity
public class Cost {
	@GraphId Long id;
	private float cost;
	@Relationship(type = "APPLIES_TO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
}
