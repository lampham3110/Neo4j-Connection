package org.neo4j.csv;

import org.neo4j.domain.Business;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public final class Neo4j {
	public static void main(String[] args){
		final SessionFactory sf = new SessionFactory("org.neo4j.domain");
		Session session = sf.openSession();
		
		Business data = session.load(Business.class, null);
		System.out.println(data);
	}
}
