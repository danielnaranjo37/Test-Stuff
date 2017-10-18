package org.usfirst.frc.team2220.robot;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
	FileWriter filewriter;
	public CSVWriter(String fileName) throws IOException{
		filewriter = new FileWriter(fileName);
	}
	public void appendRow(String[] strings) throws IOException{
		for(int i=0; i < strings.length; i++){
			if(i == (strings.length - 1)){
				filewriter.append(strings[i] + "\n");
			}
			else{
				filewriter.append(strings[i] + ",");
			}
		}
	}
	public void close() throws IOException{
		filewriter.close();
	}
}