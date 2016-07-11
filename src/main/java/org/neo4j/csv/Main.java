package org.neo4j.csv;
import org.neo4j.domain.*;
import java.util.ArrayList;
import org.neo4j.readcsvcolumn.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

public class Main {
	private static final String DB_PATH = "/Users/phaml1/Documents/Neo4j/default.graphdb/import/Data.csv";
	public enum NodeType implements Label{
		Issues,Cost,Reliability,Timeliness;
	}
	public enum RelationType implements RelationshipType{
		APPLIES_TO
	}
	
	public static void main(String[] args){
		GraphDatabaseFactory graphDatabaseFactory = new GraphDatabaseFactory();
		GraphDatabaseService graphdb = graphDatabaseFactory.newEmbeddedDatabase(DB_PATH);
		
		try(Transaction tx = graphdb.beginTx())
		{	
			Node Business = graphdb.getNodeById(null);
			issues.setProperty(", arg1);
			}
		}
	}
}
