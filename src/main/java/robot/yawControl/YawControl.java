package robot.yawControl;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.MoveToAngleAndKeep;

public class YawControl extends Turret {

  public enum TurretState {
    RTF,
    RTR,
    Homing;
  }
  private final DriveTrain driveTrain;

  private TurretState turretState;
  public YawControl(final TurretComponents turretComponents,final DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
    setTurretState(TurretState.RTR);
  }

  public void setTurretState(TurretState turretState) {
    zeroOffsetByPercent();
    if (getDefaultCommand() != null) getDefaultCommand().cancel();
    switch (turretState) {
      case RTF:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getTurretAngleRTF));
        break;
      case RTR:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getAngleRTR));
        break;
      case Homing:
        setDefaultCommand(new MoveToAngleAndKeep(this, () -> 0));
        break;
    }
    this.turretState = turretState;
  }

  @Override
  public void moveToAngle(double angle) {
    if(turretState == TurretState.RTF) {
      angle -= driveTrain.getNavXYaw();
    }
    super.moveToAngle(angle);
  }

  public double getTurretAngleRTF() {
    return driveTrain.getNavXYaw() + getAngleRTR();
  }
}
