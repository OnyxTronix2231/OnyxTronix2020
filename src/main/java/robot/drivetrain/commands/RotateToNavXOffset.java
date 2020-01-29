package robot.drivetrain.commands;

import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateToNavXOffset extends RotateToAngleNavX {
  public RotateToNavXOffset(DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    super(driveTrain, () ->
    {
      final double angleOffset = driveTrain.getNavXYaw() + angleSupplier.getAsDouble();
      if(angleOffset > 180) {
        return -360 + angleOffset;
      }
      return angleOffset;
    });
  }
}
