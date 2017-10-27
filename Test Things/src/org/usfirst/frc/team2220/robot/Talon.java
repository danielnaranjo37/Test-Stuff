package org.usfirst.frc.team2220.robot;

import java.util.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

/**
 * An example implementation of the Sensor interface, using the CANTalons as an example. 
 * Do with it what you will, but please test it when you get the chance.
 * 
 * @author Daniel Naranjo 
 *
 */
public class Talon extends CANTalon implements Sensor{
	
	private String name; 
	
	/**
	 * Standard constructor, same format as CANTalon(). 
	 * @param deviceNumber The number of the device. 
	 */
	public Talon(int deviceNumber) {
		super(deviceNumber);
		// TODO Auto-generated constructor stub
		name = "CANTalon" + deviceNumber;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 */
	public LinkedHashMap<String,Double> getAllValues(){
		LinkedHashMap<String,Double> map = new LinkedHashMap<String,Double>();
		//add whatever values like so 
		map.put(name + " Speed", super.getSpeed());
		map.put(name + " Temperature", super.getTemperature());
		map.put(name + " Output Current", super.getOutputCurrent());
		map.put(name + " Output Voltage", super.getOutputVoltage());
		
//		map.put(name + " Error", super.getError()); 
//		map.put(name + " Bus Voltage", super.getBusVoltage());
//		map.put(name + " Analog in Position", 0.0 + super.getAnalogInPosition());
//		map.put(name + " Analog in Velocity", 0.0 + super.getAnalogInVelocity());
		
		return map;
	}
}
