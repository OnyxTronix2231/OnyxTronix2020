package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretToAngle;
import robot.yawControl.YawControl;

public class MoveTurretToFieldTargetByPose extends MoveTurretToAngle {

  public MoveTurretToFieldTargetByPose(final YawControl yawControl, final DriveTrain driveTrain) {
    super(yawControl, () -> yawControl.getAngleToTargetFromPose2d(driveTrain.getPose()));
  }
}