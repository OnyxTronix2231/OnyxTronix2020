package robot.yawControl;

import static robot.yawControl.YawControlConstants.TARGET_Y;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.MoveTurretToAngleAndKeep;

public class YawControl extends Turret {

  public enum TurretState {
    RTF,
    RTR,
    HOMING;
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
      case HOMING:
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
    if (y < TARGET_Y) {
      angle = -angle;
    }
    if (angle < 180 && angle > 0) {
      side = -1;
    }
    return 180 + side * (Math.atan(Math.abs(y - TARGET_Y) / x) + angle);
  }
}
