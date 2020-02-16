package robot.yawControl;

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
      tempAngle -= driveTrain.getPose().getRotation().getDegrees();
    }
    super.moveToAngle(tempAngle);
  }

  public double getTurretAngleRTF() {
    return driveTrain.getPose().getRotation().getDegrees() + getAngleRTR();
  }
}
