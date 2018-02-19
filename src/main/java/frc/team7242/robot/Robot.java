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
import openrio.powerup.MatchData;



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

    double autonomousTime5 = 2.5;
    double autonomousTime6 = 3.96;
    double autonomousTime7 = 4.6;
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

    ScalableSpeedControllerGroup right = new ScalableSpeedControllerGroup(0.75, leftFront, leftBack);
    ScalableSpeedControllerGroup left = new ScalableSpeedControllerGroup(0.71, rightFront, rightBack);


    DifferentialDrive drive = new DifferentialDrive(left, right);


    public void robotInit() {
        m_chooser.addDefault("Default Auto", kDefaultAuto);
        m_chooser.addObject("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
        CameraServer.getInstance().startAutomaticCapture();
        left.setInverted(true);
        right.setInverted(true);

        // leftFront.setSafetyEnabled(false);
        // rightFront.setSafetyEnabled(false);
        // leftBack.setSafetyEnabled(false);
        // rightBack.setSafetyEnabled(false);
    }

    public void autonomousInit() {
        m_autoSelected = m_chooser.getSelected();
        System.out.println("Auto selected: " + m_autoSelected);
        boolean leftAuto = false; //variuable to see if the first switch from our perspective is on the left side, needed initialization so...
        boolean rightAuto = false; //variuable to see if the first switch from our perspective is on the right side, needed onitialization so...

        MatchData.OwnedSide side = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
        if (side == MatchData.OwnedSide.LEFT) {
            // OwnedSide LEFT means that the switch NEAREST to our station is on the left
            leftAuto = true;
            rightAuto = false;
        }
        else if (side == MatchData.OwnedSide.RIGHT) {
            // OwnedSide RIGHT means that the switch NEAREST to our station is on the RIGHT
            rightAuto = true;
            leftAuto = false;
        }
        else {
            // This literally shouldn't happen
            System.out.println("What is going on");
        }

        boolean leftStarting = false;
        boolean mediumStarting = false;
        boolean rightStarting = false;

        int station = DriverStation.getInstance().getLocation();
        if (station == 1) {
            leftStarting = true;
        }
        else if (station == 2) {
            mediumStarting = true;
        }
        else if (station == 3) {
            rightStarting = true;
        }

        /* boolean leftStarting = driverStick.getRawButtonPressed(8) || driverStick.getRawButtonPressed(7) ;
        boolean mediumStarting = driverStick.getRawButtonPressed(10)|| driverStick.getRawButtonPressed(9) ;
        boolean rightStarting = driverStick.getRawButtonPressed(12)|| driverStick.getRawButtonPressed(11) ; */

        boolean path7 = leftStarting && leftAuto;
        boolean path8 = leftStarting && rightAuto;
        boolean path9 = mediumStarting && leftAuto;
        boolean path10 = mediumStarting && rightAuto;
        boolean path11 = rightStarting && leftAuto;
        boolean path12 = rightStarting && rightAuto;

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

        boolean path7 = true;
        boolean path8 = driverStick.getRawButtonPressed(8);
        boolean path9 = driverStick.getRawButtonPressed(9);
        boolean path10 = driverStick.getRawButtonPressed(10);
        boolean path11 = driverStick.getRawButtonPressed(11);
        boolean path12 = driverStick.getRawButtonPressed(12);

        if(path7) { //start of program for path 7
            if (deltaTime < autonomousTime5) {
                drive.arcadeDrive(0.8, 0.0); //0.56
            } else if (autonomousTime5 < deltaTime && deltaTime < autonomousTime6) {
                drive.arcadeDrive(0.0, -0.56);
            } else if (autonomousTime6 < deltaTime && deltaTime < autonomousTime7) {
                drive.arcadeDrive(1, 0.0);
            } else if (autonomousTime7 < deltaTime && deltaTime < autonomousTime8) {
                drive.arcadeDrive(0.0, 0.0); //-0.65
            } else if (autonomousTime8 < deltaTime && deltaTime < autonomousTime9) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime9 < deltaTime && deltaTime < autonomousTime10) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime10 < deltaTime && deltaTime < autonomousTime11) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime11 < deltaTime && deltaTime < autonomousTime12) {
                drive.arcadeDrive(0, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }

        } //closing bracket for path 7

        if(path8){ //start of program of path 8

        } //closing of path 8

        if(path9){ //start of program of path 9

        } //closing of path 9

        if(path10){ //start of program of path 10

        } //closing of path 10

        if(path11){ //start of program of path 11

        } //closing of path 11

        if(path12){ //start of program of path 12
            if (deltaTime < autonomousTime5) {
                drive.arcadeDrive(1.9, 0.0); //0.56
            } else if (autonomousTime5 < deltaTime && deltaTime < autonomousTime6) {
                drive.arcadeDrive(0.0, 0.53);
            } else if (autonomousTime6 < deltaTime && deltaTime < autonomousTime7) {
                drive.arcadeDrive(0.8, 0.0);
            } else if (autonomousTime7 < deltaTime && deltaTime < autonomousTime8) {
                drive.arcadeDrive(0.0, 0.0); //-0.65
            } else if (autonomousTime8 < deltaTime && deltaTime < autonomousTime9) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime9 < deltaTime && deltaTime < autonomousTime10) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime10 < deltaTime && deltaTime < autonomousTime11) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime11 < deltaTime && deltaTime < autonomousTime12) {
                drive.arcadeDrive(0, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }
        } //closing of path 12

    } //closing bracket  for autonomous periodic

    public void teleopPeriodic() {

        double xvalue = (driverStick.getX())/-4;
        double yvalue = (driverStick.getY())*-1;
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

        double sens = 1;
        double xsens = 0.5;
        double ysens = 0.8;
        double threshold = 0;
        double tres;
        if((Math.abs(xvalue)<threshold) &&  (Math.abs(yvalue)<threshold)){
            tres = 0;
        }
        else {
            tres = 1;
        }

        double xabs = Math.abs(xvalue);
        double yabs = Math.abs(yvalue);

        // x should be a value from -1 to 1, based on the sum of x and y value
        // -1 is the robot going full speed backwards, and 1 is the robot going full speed ahead/
        //double x = (((yvalue) + xabs) * boost * -1 * braking * tres );
        // double x = ((yvalue + xabs) * 1.2 * boost * braking * -1 * tres);

        // y should be a value from 0 to 1, based on xvalue
        // -1 is the robot going full speed to the left and 1 is the robot going full speed to the right
        // double r = (xvalue * -1);

        double rightMotor =((yvalue - xvalue/sens) * (0.8) * boost * braking * 1 * tres);
        double leftMotor = ((yvalue + xvalue/sens) * (0.8) * boost * braking * tres);
        drive.tankDrive(leftMotor, rightMotor);
    }
}
