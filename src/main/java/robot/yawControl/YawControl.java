package robot.yawControl;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.turret.TurretComponents;
import robot.turret.commands.KeepAtAngle;
import robot.turret.commands.MoveToAngleAndKeep;

import java.util.function.DoubleToIntFunction;

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
        setDefaultCommand(new MoveToAngleAndKeep(this, () -> getaa() ));
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
    System.out.println(getAngle() + driveTrain.getYaw());
    return getAngle() + driveTrain.getYaw();
  }

  @Override
  public void moveToAngle(double angle) {
    if(turretState == TurretState.RTF) {
      angle -= driveTrain.getYaw();
    }
    super.moveToAngle(angle);
  }

  public double getTurretAngleRTF() {
    System.out.println("Yaw robot: " + driveTrain.getYaw());
    return driveTrain.getYaw() + getAngle();
  }
}
