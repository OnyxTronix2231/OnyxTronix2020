package robot.drivetrain.commands;

import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateToNavXOffset extends RotateToAngleNavX {
  public RotateToNavXOffset(DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    super(driveTrain, () ->
    {
      final double targetAngle = driveTrain.getNavXYaw() + angleSupplier.getAsDouble();
      if(targetAngle == -180) {
        return 180;
      }
      if(targetAngle > 180) {
        return -360 + targetAngle;
      }
      if(targetAngle < -180) {
        return 360 + targetAngle;
      }
      return targetAngle;
    });
  }
}
