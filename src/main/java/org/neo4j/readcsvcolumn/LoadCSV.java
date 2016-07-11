package org.neo4j.readcsvcolumn;

import java.io.File;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class LoadCSV{
	public enum NodeType implements Label{
		Issues, Cost, Reliability, Timeliness;
	}
	public enum RelationType implements RelationshipType{
		APPLIES_TO
	}
	private static File DB_PATH = new File("/Users/phaml1/Documents/Neo4j/default.graphdb/import/Data.csv");
	public static void Transaction() throws Exception
	{
		Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "dile0406"));
		Session session = driver.session();
		session.beginTransaction();
		driver.close();
	}
	public static void main(String[] args){
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		
		Transaction tx1 = db.beginTx();
		try{
			db.execute("USING PERIODIC COMMIT 1000\n"
					+ "LOAD CSV WITH HEADERS FROM 'file:/Data.csv' AS ROW\n"
					+ "MERGE(issues:Business{Description: ROW.Business_Solution_Description})\n"
					+ "MERGE(cost:Cost{Cost: ROW.Business_Solution_Average_Cost})\n"
					+ "MERGE(reliability:Rel{Reliability: toFloat ROW.Business_Solution_Reliability)})\n"
					+ "MERGE(time:Time{Timeliness: toFloat(ROW.Business_Solution_Timeliness)})\n"
					+ "MERGE(subre:SubjectReferenced)\n"
					+ "MERGE(issues)-[r:APPLIES_TO]->(subre)\n"
					+ "MERGE(issues)-[:APPLIES_TO]->(cost)\n"
					+ "MERGE(issues)-[:APPLIES_TO]->(reliability)\n"
					+ "MERGE(issues)-[:APPLIES_TO]->(time)\n");
			
			tx1.success();
		} finally{
			tx1.close();
		}
	}
}
