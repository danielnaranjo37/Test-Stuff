package org.usfirst.frc.team2220.robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	BufferedReader reader;
	public CSVReader(String filename) throws FileNotFoundException{
		reader = new BufferedReader(new FileReader(filename));
	}
	public String[] readRow() throws IOException{
		String line = reader.readLine();
		if (line == null){
			return null;
		}
		return line.split(",");
	}
	public void close() throws IOException{
		reader.close();
	}
}



