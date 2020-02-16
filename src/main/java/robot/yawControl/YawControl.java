package robot.yawControl;

import static robot.yawControl.YawControlConstants.targetY;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.MoveTurretToAngleAndKeep;

public class YawControl extends Turret {

  public enum TurretState {
    RTF,
    RTR,
    Homing;
  }

  private final DriveTrain driveTrain;
  private TurretState turretState;

  public YawControl(final TurretComponents turretComponents, final DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
    setTurretState(TurretState.RTR);
  }

  public void setTurretState(final TurretState turretState) {
    if (getDefaultCommand() != null) getDefaultCommand().cancel();
    switch (turretState) {
      case RTF:
        setDefaultCommand(new MoveTurretToAngleAndKeep(this, this::getTurretAngleRTF));
        break;
      case RTR:
        setDefaultCommand(new MoveTurretToAngleAndKeep(this, this::getAngleRTR));
        break;
      case Homing:
        setDefaultCommand(new MoveTurretToAngleAndKeep(this, () -> 0));
        break;
    }
    this.turretState = turretState;
  }

  @Override
  public void moveToAngle(final double angle) {
    double tempAngle = angle;
    if (turretState == TurretState.RTF) {
      tempAngle -= driveTrain.getOdometryHeading();
    }
    super.moveToAngle(tempAngle);
  }

  public double getTurretAngleRTF() {
    return driveTrain.getOdometryHeading() + getAngleRTR();
  }

  public double getAngleToTargetFromPose2d(final Pose2d pose) {
    final double x = pose.getTranslation().getX();
    final double y = pose.getTranslation().getY();
    double angle = pose.getRotation().getDegrees();
    int side = 1;
    if (y < targetY) {
      angle = -angle;
    }
    if (angle < 180 && angle > 0) {
      side = -1;
    }
    return 180 + side * (Math.atan(Math.abs(y - targetY) / x) + angle);
  }
}
