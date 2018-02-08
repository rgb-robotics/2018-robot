package frc.team7242.robot.subsystem;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {
    private Spark leftFront = new Spark(1);
    private Spark rightFront = new Spark(3);
    private Spark leftBack = new Spark(0);
    private Spark rightBack = new Spark(2);
    private SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftFront, leftBack);
    private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightFront, rightBack);
    private DifferentialDrive robotDrive = new DifferentialDrive(leftMotors, rightMotors);


    public void drive(double throttle, double turn, boolean square){
        robotDrive.arcadeDrive(throttle, turn, square);
    }
}

