package org.neo4j.repo;
import org.neo4j.noderel.Cost;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends GraphRepository<Cost>{
	
}
