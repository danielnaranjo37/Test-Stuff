package org.usfirst.frc.team2220.robot;

import java.io.IOException;
import java.util.*;
/**
 * An ArrayList plus a CSV writer that takes sensor data and writes them to a CSV file.
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

		ArrayList<String> appendList = new ArrayList<String>();

		for (Sensor sensor: sensors){ //get the value names and write them into the append list
			LinkedHashMap<String,Double> map = sensor.getAllValues(); 
			for (String key: map.keySet()){
				appendList.add(key);
			}
		}
		
		String[] append = appendList.toArray(new String[appendList.size()]);

		
		try { //append the row to the CSV 
			writer.appendRow(append);
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
		
		ArrayList<String> appendList = new ArrayList<String>(); //make String ArrayList 
		
		for (Sensor sensor : sensors){ //iterate over all sensors 
			LinkedHashMap<String,Double> map = sensor.getAllValues(); //get their values 
			
			for (String key : map.keySet()) { //iterate over the value names 
				Double d = map.get(key); //get the value there
				appendList.add( "" + d ); //add the value to the append list
			}
		}
		
		String[] append = appendList.toArray(new String[appendList.size()]);
		
		try {
			writer.appendRow(append);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;	
	}
		

	/**
	 * Adds a sensor to the ArrayList.
	 */
	public void add(Sensor sensor){
		sensors.add(sensor);
		return;
	}
}