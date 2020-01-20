package robot.turret.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngleAndKeep extends CommandBase {

  protected final DoubleSupplier supplierAngle;
  protected final Turret turret;
  protected double angle;

  public MoveToAngleAndKeep(final Turret turret, final DoubleSupplier supplierAngle) {
    this.turret = turret;
    this.supplierAngle = supplierAngle;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    angle = supplierAngle.getAsDouble();
  }

  @Override
  public void execute() {
    turret.moveToAngle(angle);
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopMotor();
  }
}
