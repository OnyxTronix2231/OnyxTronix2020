package robot.drivetrain.commands;

import robot.drivetrain.DriveTrain;

import java.util.function.DoubleSupplier;

public class RotateByAngleNavX extends RotateToAngleNavX {
  public RotateByAngleNavX(DriveTrain driveTrain, DoubleSupplier angleSupplier) {
    super(driveTrain, () -> {
      double angle = driveTrain.getNavXYaw() + angleSupplier.getAsDouble();
      if(angle > 180) {
        return angle - 360;
      } else if(angle < -180) {
        return angle + 360;
      }
      if(angle == -180) {
        return 180;
      }
      return angle;
    });
  }
}
