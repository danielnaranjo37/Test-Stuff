package org.usfirst.frc.team2220.robot;

import java.util.*;
/**
 * Intermediary interface (say that five times fast) for writing sensor data to a CSV file. 
 * This just creates a wrapper for the SensorCollection class to work with. 
 * Any class that implements this should also extend whatever Sensor they represent. 
 * @author Daniel Naranjo
 *
 */
public interface Sensor {
	//Recommended: LinkedHashMap<String,Double> allTheValues;
	
	/**
	 * Gets the sensor's name, ex. "CANTalon 15".
	 * 
	 * @return A String representing the sensor's name. 
	 */
	String getName(); //get the sensor's name
	
	/**
	 * Gets all the values out of this sensor. 
	 * 
	 * @return A HashMap with the key being the value type (temperature, voltage, etc.),
	 * and the Double being the number itself.
	 */
	LinkedHashMap<String,Double> getAllValues(); 
}
