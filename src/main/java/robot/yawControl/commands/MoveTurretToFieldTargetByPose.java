package robot.yawControl.commands;

import robot.drivetrain.DriveTrain;
import robot.turret.commands.MoveTurretToAngle;
import robot.turret.commands.MoveTurretToAngleAndKeep;
import robot.yawControl.YawControl;

public class MoveTurretToFieldTargetByPose extends MoveTurretToAngleAndKeep {

  public MoveTurretToFieldTargetByPose(final YawControl yawControl, final DriveTrain driveTrain) {
    super(yawControl, () -> yawControl.getAngleToTargetFromPose2d(driveTrain.getPose()));
  }
}