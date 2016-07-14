package org.neo4j.nodes;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

	@NodeEntity
	public class Reliability {
		@GraphId Long id;
		private float reliability;
		@Relationship(type = "APPLIES_TO")
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public float getReliability() {
			return reliability;
		}
		public void setReliability(float reliability) {
			this.reliability = reliability;
		}
	}
