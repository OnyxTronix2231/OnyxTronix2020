package robot.yawControl;

import static robot.yawControl.YawControlConstants.ONE_EIGHTY_DEGREES;
import static robot.yawControl.YawControlConstants.SIDE_NUMBER;
import static robot.yawControl.YawControlConstants.TARGET_Y;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.MoveTurretToAngleAndKeep;

public class YawControl extends Turret {

  private double initRobotAngleRTF;

  public enum TurretState {
    RTF,
    RTR,
    HOMING
  }

  private final DriveTrain driveTrain;
  private TurretState turretState;
  private double robotHeadingOffset;

  public YawControl(final TurretComponents turretComponents, final DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
    robotHeadingOffset = 0;
    setTurretState(TurretState.RTR);
  }

  public void setTurretState(final TurretState turretState) {
    if (getDefaultCommand() != null) getDefaultCommand().cancel();
    switch (turretState) {
      case RTF:
        robotHeadingOffset = getRobotHeading();
      case RTR:
        setDefaultCommand(new MoveTurretToAngleAndKeep(this, this::getAngleRTR));
        break;
      case HOMING:
        setDefaultCommand(new MoveTurretToAngleAndKeep(this, () -> 0));
        break;
    }
    this.turretState = turretState;
  }

  @Override
  public void initMoveMotionMagic() {
    initRobotAngleRTF = getRobotHeading();
  }

  @Override
  public void moveToAngle(final double angle) {
    double tempAngle = angle;
    if (turretState == TurretState.RTF) {
      tempAngle -= getRobotHeading() - initRobotAngleRTF;
      robotHeadingOffset = driveTrain.getRawRobotHeading();
    }
    super.moveToAngle(tempAngle);
  }

  public double getTurretAngleRTF() {
    return getRobotHeading() + getAngleRTR();
  }

  public double getRobotHeading() {
    return driveTrain.getRawRobotHeading();
  }

  public double getAngleToTargetFromPose2d(final Pose2d pose) {
    final double x = pose.getTranslation().getX();
    final double y = pose.getTranslation().getY();
    double angle = pose.getRotation().getDegrees();
    int side = SIDE_NUMBER;
    if (y < TARGET_Y) {
      angle = -angle;
    }
    if (angle < ONE_EIGHTY_DEGREES && angle > 0) {
      side = -SIDE_NUMBER;
    }
    return ONE_EIGHTY_DEGREES + side * (Math.atan(Math.abs(y - TARGET_Y) / x) + angle);
  }
}
