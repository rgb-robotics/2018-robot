/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team7242.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import frc.team7242.robot.subsystem.ScalableSpeedControllerGroup;
import edu.wpi.first.wpilibj.CameraServer;


public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	Joystick driverStick = new Joystick(0);



	// double autonomousTime = 3;
	//double autonomousTime2 = 4;
	// double autonomousTime3 = 5.5;
	// double autonomousTime4 = 6;

	double autonomousTime5 = 1.5;
	double autonomousTime6 = 3.5;
	double autonomousTime7 = 4.5;
	double autonomousTime8 = 6;
	double autonomousTime9 = 9;
	double autonomousTime10= 10;
	double autonomousTime11= 11.5;
	double autonomousTime12= 12;


	public Spark leftFront = new Spark(0);
	public Spark rightFront = new Spark(2);
	public Spark leftBack = new Spark(1);
	public Spark rightBack = new Spark(3);

	double autonomousStartTime;

	///ScalableSpeedControllerGroup right = new ScalableSpeedControllerGroup(0.75, leftFront, leftBack);
	//ScalableSpeedControllerGroup left = new ScalableSpeedControllerGroup(0.72, rightFront, rightBack);



	//DifferentialDrive drive = new DifferentialDrive(left, right);


	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);
		CameraServer.getInstance().startAutomaticCapture();
		//left.setInverted(true);
		//right.setInverted(true);

		// leftFront.setSafetyEnabled(false);
		// rightFront.setSafetyEnabled(false);
		// leftBack.setSafetyEnabled(false);
		// rightBack.setSafetyEnabled(false);
	}

	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);

		autonomousStartTime = Timer.getFPGATimestamp();

	}

	public void autonomousPeriodic() {
		double deltaTime = Timer.getFPGATimestamp() - autonomousStartTime;
		// if (deltaTime < autonomousTime) {
		//  drive.arcadeDrive(0.80, 0.0);
		//} else if (autonomousTime < deltaTime && deltaTime < autonomousTime2) {
		//drive.arcadeDrive(0.0, 0.0);
		// } else if (autonomousTime2 < deltaTime && deltaTime < autonomousTime3) {
		//    drive.arcadeDrive(0.0, -0.56);
		// } else if (autonomousTime3 < deltaTime && deltaTime < autonomousTime4) {
		//     drive.arcadeDrive(1, 0.0);
		// } else {
		//     drive.arcadeDrive(0.0, 0.0);
		// }

		// }
		/*
		if(deltaTime < autonomousTime5){
			drive.arcadeDrive(0.0, 0.56);
		}else if(autonomousTime5 < deltaTime && deltaTime < autonomousTime6){
			drive.arcadeDrive(0.8, 0.0);
		}else if(autonomousTime6 < deltaTime && deltaTime < autonomousTime7){
			drive.arcadeDrive(0.0, 0.0);
		}else if(autonomousTime7 < deltaTime && deltaTime < autonomousTime8){
			drive.arcadeDrive(0.0, -0.65);
		}else if(autonomousTime8 < deltaTime && deltaTime < autonomousTime9) {
			drive.arcadeDrive(0.8, 0.0);
		}else if(autonomousTime9 < deltaTime && deltaTime < autonomousTime10){
			drive.arcadeDrive(0.0, 0.0);
		}else if(autonomousTime10 < deltaTime && deltaTime < autonomousTime11){
			drive.arcadeDrive(0.0, -0.56);
		}else if(autonomousTime11 < deltaTime && deltaTime < autonomousTime12){
			drive.arcadeDrive(1, 0.0);
		}else{
			drive.arcadeDrive(0.0, 0.0);
		}
		*/
	}

	public void teleopPeriodic() {

		double xvalue = driverStick.getX();
		double yvalue = driverStick.getY();
		double boost;
		double braking;

		boolean trigger = driverStick.getRawButton(1);
		if (trigger) {
			boost = 2;
		} else {
			boost = 1;
		}

		boolean brake = driverStick.getRawButton(2);
		if (brake){
			braking = 0;
		}
		else {
			braking = 1;
		}

		double sens = 2;
		double threshold = 0.1;
		double tres;
		if((Math.abs(xvalue)<threshold) &&  (Math.abs(yvalue)<threshold)){
			tres = 0;
		}
		else {
			tres = 1;
		}


		rightFront.set((yvalue - xvalue/sens) * (0.5) * boost * braking * 1 * tres);
		rightBack.set((yvalue - xvalue/sens) * (0.5) * boost * braking * 1 * tres);
		leftFront.set((yvalue + xvalue/sens) * (-0.5) * boost * braking * tres);
		leftBack.set((yvalue + xvalue/sens) * (-0.5) * boost * braking * tres);
	}
}
