package frc.team7242.robot.subsystem;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class ScalableSpeedControllerGroup extends SpeedControllerGroup {
    private double scalar = 1.0;
    public ScalableSpeedControllerGroup(double scalar, SpeedController speedController, SpeedController... speedControllers) {
        super(speedController, speedControllers);
        this.scalar = scalar;
    }

    @Override
    public void set(double value) {
        super.set(scalar * value);
    }
}
