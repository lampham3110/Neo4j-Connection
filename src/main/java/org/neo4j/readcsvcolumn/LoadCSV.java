package org.neo4j.readcsvcolumn;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.neo4j.domain.Business;


public class LoadCSV {
	public static void main(String[] args){
		List<Business> businesses = readBusinessFromCSV("Users/phaml1/Documents/Neo4j/default.graphdb/import/Data.csv");
		for(Business b: businesses){
			System.out.println(b);
		}
	}
	private static List<Business> readBusinessFromCSV(String fileName){
		List<Business> business = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)){
			
			String line = br.readLine();
			while(line != null){
				String[] attributes = line.split(",");
				Business busin = createIssues(attributes);
				business.add(busin);
				line = br.readLine();
			}
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		return business;
	}
	private static Business createIssues(String[] metadata){
		String issue = metadata[0];
		float cost = Float.parseFloat(metadata[1]);
		float reliability = Float.parseFloat(metadata[2]);
		float timeliness = Float.parseFloat(metadata[3]);
		
		return new Business(issue,cost,reliability,timeliness);
	}
}

