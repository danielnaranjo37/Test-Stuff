package org.usfirst.frc.team2220.robot;

import java.io.IOException;
import java.util.*;
/**
 * An ArrayList plus a CSV writer that takes sensor data and writes them to a CSV file.
 * I'm a bit worried that the Map that's created by the Maps created by the writeHeaders and 
 * writeAllValues methods won't match in the CSV, so maybe we could add some safeguard for that. 
 * @author Daniel Naranjo
 *
 */
public class SensorCollection {
	ArrayList<Sensor> sensors;
	CSVWriter writer; //new CSVWriter("/home/lvuser/Test.CSV");
	
	/**
	 * Your standard constructor. 
	 * Sets up the CSVWriter and an ArrayList of Sensors. 
	 */
	public SensorCollection(){
		sensors = new ArrayList<Sensor>();

		try {
			writer = new CSVWriter("/home/lvuser/Test.CSV");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Writes sensor value names as headers into the CSV file.
	 * Place before writing the data in. 
	 */
	private void writeHeaders(){

		ArrayList<String> toAppend = new ArrayList<String>();
		
		for (Sensor sensor: sensors){ //get the value names and write them into the append list
			Map<String,Double> map = sensor.getAllValues(); 
			for (String key: map.keySet()){
				toAppend.add(key);
			}
		}
		
		String[] appending = (String[]) toAppend.toArray();
		
		try { //append the row to the CSV 
			writer.appendRow(appending);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return; 
	}
	
	/**
	 * Writes the actual values to the CSV files.
	 */
	public void writeAllValues(){
		writeHeaders(); //set up the headers
		
		ArrayList<String> toAppend = new ArrayList<String>(); 
		
		for (Sensor sensor : sensors){ //get the data and write them into the append list 
			Map <String,Double> map = sensor.getAllValues();
			for (String key : map.keySet()){
				toAppend.add(key);
			}
		}
		
		String[] appending = (String[]) toAppend.toArray();
		
		try {
			writer.appendRow(appending);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Adds a sensor to the ArrayList.
	 */
	public void addSensor(Sensor sensor){
		sensors.add(sensor);
		return;
	}
}