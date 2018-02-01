/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team7242.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	Joystick driverStick = new Joystick(0);
	XboxController xboxdriver = new XboxController(1); //xbox controller in port 1
	
	Spark leftFront = new Spark(0);
	Spark rightFront = new Spark(1);
	Spark leftBack = new Spark(2);
	Spark rightBack = new Spark(3);
	
	Timer time;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		timer = new Timer();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		m_timer.reset();
		m_timer.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		leftFront.set(0.5);
		rightFront.set(0.5);
		leftBack.set(0.5);
		right1Back.set(0.5);
		if(m_timer.get()<7.0){
			leftFront.set(0.5);
			rightFront.set(0.5);
			leftBack.set(0.5);
			right1Back.set(0.5);
		}else{
			leftFront.set(0.0);
			rightFront.set(0.0);
			leftBack.set(0.0);
			right1Back.set(0.0);
			
		}
			
			
		}
	}
		

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		//if using joystick
		double leftStickValue = driverStick.getRawAxis(1);
		double rightStickValue = driverStick.getRawAxis(3);
		
		//if using xbox, idk how to map xbox
	//	double leftStickvalue = xboxdriver.getX();
	//	double rightStickvalue = xboxdriver.getY();
		
		
		
		// runs motors at speed
		leftFront.set(leftStickValue);
		leftBack.set(leftStickValue);
		rightFront.set(rightStickValue);
		rightBack.set(rightStickValue);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
