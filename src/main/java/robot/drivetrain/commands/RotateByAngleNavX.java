package robot.drivetrain.commands;

import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateByAngleNavX extends RotateToAngleNavX {
  public RotateByAngleNavX(DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    super(driveTrain, () -> driveTrain.getNavXYaw() + angleSupplier.getAsDouble());
  }
}
