/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team7242.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

    double autonomousTime = 5;

    double autonomousStartTime;

    ScalableSpeedControllerGroup right = new ScalableSpeedControllerGroup(0.75, new Spark(0), new Spark(1));
    ScalableSpeedControllerGroup left = new ScalableSpeedControllerGroup(1.0, new Spark(2), new Spark(3));

    DifferentialDrive drive = new DifferentialDrive(left, right);


    public void robotInit() {
        m_chooser.addDefault("Default Auto", kDefaultAuto);
        m_chooser.addObject("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        CameraServer.getInstance().startAutomaticCapture();
        left.setInverted(true);
        right.setInverted(true);


    }

    public void autonomousInit() {
        m_autoSelected = m_chooser.getSelected();
        System.out.println("Auto selected: " + m_autoSelected);

        autonomousStartTime = Timer.getFPGATimestamp();

    }

    public void autonomousPeriodic() {
        double deltaTime = Timer.getFPGATimestamp() - autonomousStartTime;
        if (deltaTime < autonomousTime) {
            drive.arcadeDrive(0.5, 0.0);
        } else {
            drive.arcadeDrive(0.0, 0.0);
        }


    }

    public void teleopPeriodic() {

        double xvalue = driverStick.getX();
        double yvalue = driverStick.getY();
        double boost;

        boolean trigger = driverStick.getRawButton(1);
        if (trigger) {
            boost = 2.5;
        } else {
            boost = 1;
        }

        right.set((yvalue + xvalue) * (-0.4) * boost);
        left.set((yvalue - xvalue) * (0.4) * boost);
    }
}


