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
  public YawControl(TurretComponents turretComponents, DriveTrain driveTrain) {
    super(turretComponents);
    this.driveTrain = driveTrain;
    setTurretState(TurretState.RTR);
  }

  public void setTurretState(TurretState turretState) {
    zeroOffsetByPercent();
    if (getDefaultCommand() != null) getDefaultCommand().cancel();
    switch (turretState) {
      case RTF:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getaa));
        break;
      case RTR:
        setDefaultCommand(new MoveToAngleAndKeep(this, this::getAngle));
        break;
      case Homing:
        setDefaultCommand(new MoveToAngleAndKeep(this, () -> 0));
        break;
    }
    this.turretState = turretState;
  }

  private double getaa(){
    System.out.println(getAngle() + driveTrain.getNavXYaw());
    return getAngle() + driveTrain.getNavXYaw();
  }

  @Override
  public void moveToAngle(double angle) {
    if(turretState == TurretState.RTF) {
      angle -= driveTrain.getNavXYaw();
    }
    super.moveToAngle(angle);
  }

  public double getTurretAngleRTF() {
    System.out.println("Yaw robot: " + driveTrain.getNavXYaw());
    return driveTrain.getNavXYaw() + getAngle();
  }
}
