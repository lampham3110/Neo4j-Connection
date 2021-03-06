package org.neo4j.connection;

import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import.org.neo4j.driver.v1.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;
import static org.neo4j.io.fs.FileUtils.deleteRecursively;
public class Neo4j{
	//Define Nodes//
	Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j","dile0406"));
	Session session = driver.session();

	public enum NodeType implements Label{
		Issues, Cost, Reliability, Timeliness;
	}
	//Define Relationship//
	public enum RelationType implements RelationshipType{
		APPLIES_TO
	}
	
	String rows = "";
	String nodeResult;
	String resultString;
	String columnString;
	
	////Set up a path to Database///
	public static final File DB_PATH = new File("/Users/phaml1/Documents/Neo4j/default.graphdb/import/Data.csv");
	
	public static void main(String[] args){
		Neo4j test = new Neo4j();
		test.run();
	}
	void run()
	{	
		clear();
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		/// Initialize a connection and load the query///
		try(Transaction tx1 = db.beginTx();
				Result result = db.execute("MATCH(b:Business)-[:APPLIES_TO]->(e:Time) RETURN b,e"))
		{
			///Check for more results and retrieve///
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
			
		try (Transaction something = db.beginTx();
				Result result1 = db.execute("MATCH(b:Business)-[:APPLIES_TO]->(e:Time) RETURN b,e"))
		{	
			//Iterate through a column ///
			Iterator<Node> n_column = result.columnAs("Business");
			for(Node node: IteratorUtil.asIterable(n_column))
			{
				nodeResult = node + ": " + node.getProperties("Description");
			}
			List<String> columns = result.columns();
			columnString = columns.toString();
			resultString = db.execute("MATCH(b:Business)-[:APPLIES_TO]->(e:Time) RETURN b,e").resultAsString();
		}
		
		db.shutdown();
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
	private void clear()
    {
        try
        {
            deleteRecursively( DB_PATH );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        	}
    	}
	session.close();
	driver.close();
	}
