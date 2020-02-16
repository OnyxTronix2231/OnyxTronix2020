package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.commands.MoveTurretToAngle;
import robot.yawControl.YawControl;

import java.util.function.DoubleSupplier;

public class MoveTurretToFieldTargetByPose extends MoveTurretToAngle {

  private final YawControl yawControl;
  private final DriveTrain driveTrain;

  public MoveTurretToFieldTargetByPose(final Turret turret, final DoubleSupplier angleSupplier,
                                       final YawControl yawControl, final DriveTrain driveTrain) {
    super(turret, angleSupplier);
    this.yawControl = yawControl;
    this.driveTrain = driveTrain;
  }

  @Override
  public void initialize() {
    angle = yawControl.getAngleToTargetFromPose2d(driveTrain.getPose());
  }
}