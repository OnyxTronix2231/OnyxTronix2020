package robot.yawControl;

import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.MoveToAngleAndKeep;

public class YawControl extends Turret {

  enum TurretState {
    RTF,
    RTR,
    Homing
  }

  private final DriveTrain driveTrain;

  public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
  }

  public void setTurretState(TurretState turretState) {
    switch (turretState) {
      case RTF:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getTurretAngleRTF));
        break;
      case RTR:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getAngle));
        break;
      case Homing:
        setDefaultCommand(new MoveToAngleAndKeep(this, () -> 0));
        break;
    }
  }

  public double getTurretAngleRTF() {
    return driveTrain.getAngle() + getAngle();
  }
}
