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
    //updates the current angle of the turret only when the angle supplier changes
    // that way the turret doesn't "chase it's own tail"
    if(angle != angleSupplier.getAsDouble()) {
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