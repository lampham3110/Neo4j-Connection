package org.neo4j.readcsvcolumn;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.neo4j.driver.v1.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.*;
import org.neo4j.helpers.collection.Iterators;
import static org.neo4j.io.fs.FileUtils.deleteRecursively;

public class LoadCSV{
	public enum NodeType implements Label{
		Issues, Cost, Reliability, Timeliness;
	}
	public enum RelationType implements RelationshipType{
		APPLIES_TO
	}
	
	String rows = "";
	String nodeResult;
	String resultString;
	String columnString;
	private static File DB_PATH = new File("/Users/phaml1/Documents/Neo4j/default.graphdb/import/Data.csv");
	/*public static void Transaction() throws Exception
	{
		Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "dile0406"));
		Session session = driver.session();
		session.beginTransaction();
		driver.close();
	}
	*/
	public static void main(String[] args){
		LoadCSV Query = new LoadCSV();
		Query.run();
	}
	
	void run()
	{	

		clearDbPath();
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		
		try(Transaction tx1 = db.beginTx();
				Result result = db.execute("MATCH (n) RETURN n"))
		{
			while(result.hasNext())
			{
				while ( result.hasNext() )
			    {
			        Map<String,Object> row = result.next();
			        for ( Entry<String,Object> column : row.entrySet() )
			        {
			            rows += column.getKey() + ": " + column.getValue() + "; ";
			        }
			        rows += "\n";
			}
		}
		/*
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
		*/
		}
	}
	private void clearDbPath(){
		try{
			deleteRecursively(DB_PATH);
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}
