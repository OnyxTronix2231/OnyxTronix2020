package robot.yawControl;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.KeepAtAngle;
import robot.turret.commands.MoveToAngleAndKeep;

public class YawControl extends Turret {



  public enum TurretState {
    RTF,
    RTR,
    Homing;
  }
  private final DriveTrain driveTrain;

  private TurretState turretState;
  private double angleOffset;
  public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
    angleOffset = 0;
    setTurretState(TurretState.RTR);
  }

  public void setTurretState(TurretState turretState) {
    System.out.println(turretState);
    if(getDefaultCommand() != null) getDefaultCommand().cancel();
    switch (turretState) {
      case RTF:
        setDefaultCommand(new KeepAtAngle(this, () -> getTurretAngleRTF() + getOffsetByPercent()));
        break;
      case RTR:
        setDefaultCommand(new KeepAtAngle(this, () -> getAngle() + getOffsetByPercent()));
        break;
      case Homing:
        setDefaultCommand(new KeepAtAngle(this, this::getOffsetByPercent));
        break;
    }
    this.turretState = turretState;
  }

  public void setOffsetByPercent(double percent) {
    angleOffset += percent * 0.1;
  }

  public double getOffsetByPercent() {
    return angleOffset;
  }

  public double getTurretAngleRTF() {
    return driveTrain.getYaw() + getAngle();
  }
}
