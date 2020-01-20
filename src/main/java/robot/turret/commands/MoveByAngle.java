package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveByAngle extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier angleSupplier;
  private double angle;
  private double turretAngle;

  public MoveByAngle(final Turret turret, final DoubleSupplier angleSupplier) {
    this.turret = turret;
    this.angleSupplier = angleSupplier;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    turretAngle = turret.getAngleRTR();
    angle = angleSupplier.getAsDouble();
  }

  @Override
  public void execute() {
    if(angle != angleSupplier.getAsDouble()) {
      turretAngle = turret.getAngleRTR();
      angle = angleSupplier.getAsDouble();
    }
    turret.moveToAngle(turretAngle + angle);
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopMotor();
  }

}
