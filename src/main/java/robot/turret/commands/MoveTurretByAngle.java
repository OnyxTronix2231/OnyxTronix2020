package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretByAngle extends CommandBase {

  private final Turret turret;
  private final DoubleSupplier angleSupplier;
  private double angle;
  private double turretAngle;

  public MoveTurretByAngle(final Turret turret, final DoubleSupplier angleSupplier) {
    this.turret = turret;
    this.angleSupplier = angleSupplier;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    turretAngle = turret.getAngleRTR();
    angle = angleSupplier.getAsDouble();
  }

  /**
   * updates the current angle of the turret only when the angle supplier changes
   * that way the turret doesn't "chase it's own tail"
   */
  @Override
  public void execute() {
    if (angle != angleSupplier.getAsDouble()) {
      turretAngle = turret.getAngleRTR();
      angle = angleSupplier.getAsDouble();
    }
    turret.moveToAngle(turretAngle + angle);
  }

  @Override
  public void end(final boolean interrupted) {
    turret.stopMotor();
  }
}
