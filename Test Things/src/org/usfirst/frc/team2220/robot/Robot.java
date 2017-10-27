package org.usfirst.frc.team2220.robot;

import java.io.IOException;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Talon rightMaster = new Talon(15);
	Talon rightSlave  = new Talon(6);
	Talon leftMaster  = new Talon(8);
	Talon leftSlave   = new Talon(2);
	
//	CSVWriter writer; //new CSVWriter("/home/lvuser/Test.CSV");
	
	Joystick joystick = new Joystick(0);
	
	SensorCollection sensors = new SensorCollection();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		rightSlave.changeControlMode(TalonControlMode.Follower);
		rightSlave.set(rightMaster.getDeviceID());
		
		leftSlave.changeControlMode(TalonControlMode.Follower);
		leftSlave.set(leftMaster.getDeviceID());	
		leftMaster.setInverted(true);
		
		sensors.add(rightMaster); 
		
//		try {
//			writer = new CSVWriter("/home/lvuser/Test.CSV"); 
//			String[] headers = new String[]{
//				"Left Deadzone Output",
//				"Right Deadzone Output",
//				"Right Joystick Raw Axis",
//				"Left Joystick Raw Axis",
//			};
//			writer.appendRow(headers);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
		
	/**
	 * A different deadzone function that also works on math. 
	 * It won't do anything between 0.2 and -0.2, and afterwards it scales up linearly as you approach 1 or -1. 
	 * Personally, I don't like it, but I'm also refusing to drive in competition, so you do you. 
	 * Use at your own risk. 
	 */
	@Deprecated
	@SuppressWarnings("unused")
	private double deadzoneLinear(double joystickVal){
		if (Math.abs(joystickVal) <= .2){
			return 0.0;
		}
		else if (joystickVal > 0 )
			return (joystickVal - .2)/.8;
		else 
			return (joystickVal + .2)/.8;
	}
	
	/**
	 * Deadzone function that works using the power of math. 
	 * The graph of it looks cubed, but it's actually to the 1.8th power (almost squared).
	 * Multiplies the input joystick value to the power of 1.8 by 1 or -1 depending on its original 
	 * sign.
	 * 
	 * @param joystickVal
	 * @return
	 */
	private double deadzoneAlmostSquared(double joystickVal){
			double signum = Math.signum(joystickVal);
			return signum * Math.pow(Math.abs(joystickVal),1.8); 
	}	

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		double leftDeadPut = deadzoneAlmostSquared(joystick.getRawAxis(1));
		leftMaster.set(leftDeadPut);
		double rightDeadPut = deadzoneAlmostSquared(joystick.getRawAxis(5));
		rightMaster.set(rightDeadPut);
		
		sensors.writeAllValues(); 
		
//		SmartDashboard.putNumber("Left Deadzone Output", leftDeadPut);
//		SmartDashboard.putNumber("Right Deadzone Output", rightDeadPut);
//		
//		SmartDashboard.putNumber("Right Joystick Raw Axis", joystick.getRawAxis(5));
//		SmartDashboard.putNumber("Left Joystick Raw Axis", joystick.getRawAxis(1));
//		
//		SmartDashboard.putNumber("Right Master Temperature", rightMaster.getTemperature());
//		SmartDashboard.putNumber("Right Master Current", rightMaster.getOutputCurrent());
//		SmartDashboard.putNumber("Right Master Voltage", rightMaster.getOutputVoltage());
//
//		SmartDashboard.putNumber("Right Slave Temperature", rightSlave.getTemperature());
//		SmartDashboard.putNumber("Right Slave Current", rightSlave.getOutputCurrent());
//		SmartDashboard.putNumber("Right Slave Voltage", rightSlave.getOutputVoltage());
//		
//		SmartDashboard.putNumber("Left Master Temperature", leftMaster.getTemperature());
//		SmartDashboard.putNumber("Left Master Current", leftMaster.getOutputCurrent());
//		SmartDashboard.putNumber("Left Master Voltage", leftMaster.getOutputVoltage());
//		
//		SmartDashboard.putNumber("Left Slave Temperature", leftSlave.getTemperature());
//		SmartDashboard.putNumber("Left Slave Current", leftSlave.getOutputCurrent());
//		SmartDashboard.putNumber("Left Slave Voltage", leftSlave.getOutputVoltage());
		
//		String[] strings = new String[]{
//				"" + leftDeadPut,
//				"" + rightDeadPut,
//				"" + joystick.getRawAxis(5),
//				"" + joystick.getRawAxis(1),
//		};
//		try {
//			writer.appendRow(strings);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}








