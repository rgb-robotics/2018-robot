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

    double autonomousTime5A = 2.9;
    double autonomousTime6A = 4.06;
    double autonomousTime7A = 4.6;
    double autonomousTime8A = 12.5;
    double autonomousTime9A = 13.2;
    double autonomousTime10A= 14.7;
    double autonomousTime11A= 15.37;
    double autonomousTime12A= 15.99;

    double autonomousTime5B = 2.9;
    double autonomousTime6B = 4.06;
    double autonomousTime7B = 4.6;
    double autonomousTime8B = 12.5;
    double autonomousTime9B = 13.2;
    double autonomousTime10B= 14.7;
    double autonomousTime11B= 15.37;
    double autonomousTime12B= 15.99;

    double autonomousTime5C = 2.9;
    double autonomousTime6C = 4.06;
    double autonomousTime7C = 4.6;
    double autonomousTime8C = 12.5;
    double autonomousTime9C = 13.2;
    double autonomousTime10C = 14.7;
    double autonomousTime11C = 15.37;
    double autonomousTime12C = 15.99;

    boolean path7;
    boolean path8;
    boolean path9;
    boolean path10;
    boolean path11;
    boolean path12;


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

        path7 = leftStarting && leftAuto;
        path8 = leftStarting && rightAuto;
        path9 = mediumStarting && leftAuto;
        path10 = mediumStarting && rightAuto;
        path11 = rightStarting && leftAuto;
        path12 = rightStarting && rightAuto;

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
       boolean path7 = true;
        boolean path8 = driverStick.getRawButtonPressed(8);
        boolean path9 = driverStick.getRawButtonPressed(9);
        boolean path10 = driverStick.getRawButtonPressed(10);
        boolean path11 = driverStick.getRawButtonPressed(11);
        boolean path12 = driverStick.getRawButtonPressed(12);
*/
        if(path7) { //start of program for path 7
            if (deltaTime < autonomousTime5A) {
                drive.arcadeDrive(0.8, 0.0); //0.56
            } else if (autonomousTime5A < deltaTime && deltaTime < autonomousTime6A) {
                drive.arcadeDrive(0.0, -0.56);
            } else if (autonomousTime6A < deltaTime && deltaTime < autonomousTime7A) {
                drive.arcadeDrive(2, 0.0);
            } else if (autonomousTime7A < deltaTime && deltaTime < autonomousTime8A) {
                drive.arcadeDrive(0.0, 0.0); //-0.65
            } else if (autonomousTime8A < deltaTime && deltaTime < autonomousTime9A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime9A < deltaTime && deltaTime < autonomousTime10A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime10A < deltaTime && deltaTime < autonomousTime11A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime11A < deltaTime && deltaTime < autonomousTime12A) {
                drive.arcadeDrive(0, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }

        } //closing bracket for path 7

        if(path8){ //start of program of path 8
            if (deltaTime < autonomousTime5B) {
                drive.arcadeDrive(0.6, 0.0); //0.56
            } else if (autonomousTime5B < deltaTime && deltaTime < autonomousTime6B) {
                drive.arcadeDrive(0.0, -0.62);
            } else if (autonomousTime6B < deltaTime && deltaTime < autonomousTime7B) {
                drive.arcadeDrive(0, 0.0);
            } else if (autonomousTime7B < deltaTime && deltaTime < autonomousTime8B) {
                drive.arcadeDrive(0.7, 0.0); //-0.65
            } else if (autonomousTime8B < deltaTime && deltaTime < autonomousTime9B) {
                drive.arcadeDrive(0.0, 0.65);
            } else if (autonomousTime9B < deltaTime && deltaTime < autonomousTime10B) {
                drive.arcadeDrive(0.8, 0.0);
            } else if (autonomousTime10B < deltaTime && deltaTime < autonomousTime11B) {
                drive.arcadeDrive(0.0, 0.67);
            } else if (autonomousTime11B < deltaTime && deltaTime < autonomousTime12B) {
                drive.arcadeDrive(2, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }
        } //closing of path 8

        if(path9){ //start of program of path 9

            if (deltaTime < autonomousTime5C) {
                drive.arcadeDrive(0.6, 0.0); //0.56
            } else if (autonomousTime5C < deltaTime && deltaTime < autonomousTime6C) {
                drive.arcadeDrive(0.0, 0.56);
            } else if (autonomousTime6C < deltaTime && deltaTime < autonomousTime7C) {
                drive.arcadeDrive(0, 0.0);
            } else if (autonomousTime7C < deltaTime && deltaTime < autonomousTime8C) {
                drive.arcadeDrive(0.36, 0.0); //-0.65
            } else if (autonomousTime8C < deltaTime && deltaTime < autonomousTime9C) {
                drive.arcadeDrive(0.0, -0.7);
            } else if (autonomousTime9C < deltaTime && deltaTime < autonomousTime10C) {
                drive.arcadeDrive(0.79, 0.0);
            } else if (autonomousTime10C < deltaTime && deltaTime < autonomousTime11C) {
                drive.arcadeDrive(0.0, -0.71);
            } else if (autonomousTime11C < deltaTime && deltaTime < autonomousTime12C) {
                drive.arcadeDrive(3, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }

        } //closing of path 9

        if(path10){ //start of program of path 10

            if (deltaTime < autonomousTime5C) {
                drive.arcadeDrive(0.6, 0.0);
            } else if (autonomousTime5C < deltaTime && deltaTime < autonomousTime6C) {
                drive.arcadeDrive(0.0, -0.62);
            } else if (autonomousTime6C < deltaTime && deltaTime < autonomousTime7C) {
                drive.arcadeDrive(0, 0.0);
            } else if (autonomousTime7C < deltaTime && deltaTime < autonomousTime8C) {
                drive.arcadeDrive(0.36, 0.0);
            } else if (autonomousTime8C < deltaTime && deltaTime < autonomousTime9C) {
                drive.arcadeDrive(0.0, 0.61);
            } else if (autonomousTime9C < deltaTime && deltaTime < autonomousTime10C) {
                drive.arcadeDrive(0.72, 0.0);
            } else if (autonomousTime10C < deltaTime && deltaTime < autonomousTime11C) {
                drive.arcadeDrive(0.0, 0.62);
            } else if (autonomousTime11C < deltaTime && deltaTime < autonomousTime12C) {
                drive.arcadeDrive(2, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }

        } //closing of path 10

        if(path11){ //start of program of path 11
            if (deltaTime < autonomousTime5B) {
                drive.arcadeDrive(0.6, 0.0); //0.56
            } else if (autonomousTime5B < deltaTime && deltaTime < autonomousTime6B) {
                drive.arcadeDrive(0.0, 0.56);
            } else if (autonomousTime6B < deltaTime && deltaTime < autonomousTime7B) {
                drive.arcadeDrive(0, 0.0);
            } else if (autonomousTime7B < deltaTime && deltaTime < autonomousTime8B) {
                drive.arcadeDrive(0.7, 0.0); //-0.65
            } else if (autonomousTime8B < deltaTime && deltaTime < autonomousTime9B) {
                drive.arcadeDrive(0.0, -0.7);
            } else if (autonomousTime9B < deltaTime && deltaTime < autonomousTime10B) {
                drive.arcadeDrive(0.79, 0.0);
            } else if (autonomousTime10B < deltaTime && deltaTime < autonomousTime11B) {
                drive.arcadeDrive(0.0, -0.71);
            } else if (autonomousTime11B < deltaTime && deltaTime < autonomousTime12B) {
                drive.arcadeDrive(3, 0.0);
            } else {
                drive.arcadeDrive(0.0, 0.0);
            }
        } //closing of path 11

        if(path12){ //start of program of path 12
            if (deltaTime < autonomousTime5A) {
                drive.arcadeDrive(0.8, 0.0); //0.56
            } else if (autonomousTime5A < deltaTime && deltaTime < autonomousTime6A) {
                drive.arcadeDrive(0.0, 0.56);
            } else if (autonomousTime6A < deltaTime && deltaTime < autonomousTime7A) {
                drive.arcadeDrive(2, 0.0);
            } else if (autonomousTime7A < deltaTime && deltaTime < autonomousTime8A) {
                drive.arcadeDrive(0.0, 0.0); //-0.65
            } else if (autonomousTime8A < deltaTime && deltaTime < autonomousTime9A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime9A < deltaTime && deltaTime < autonomousTime10A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime10A < deltaTime && deltaTime < autonomousTime11A) {
                drive.arcadeDrive(0.0, 0.0);
            } else if (autonomousTime11A < deltaTime && deltaTime < autonomousTime12A) {
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
