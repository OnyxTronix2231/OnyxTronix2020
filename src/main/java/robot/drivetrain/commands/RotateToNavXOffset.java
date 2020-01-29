package robot.drivetrain.commands;

import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateToNavXOffset extends RotateToAngleNavX {
  public RotateToNavXOffset(DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    super(driveTrain, () -> driveTrain.getNavXYaw() + angleSupplier.getAsDouble());
  }
}
