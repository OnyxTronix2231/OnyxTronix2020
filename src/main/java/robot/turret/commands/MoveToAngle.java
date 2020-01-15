package robot.turret.commands;

import static robot.turret.TurretConstants.TOLERANCE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends CommandBase {

  private final DoubleSupplier supplierAngle;
  private final Turret turret;
  private final boolean updateAngle;
  private double angle;

  public MoveToAngle(final Turret turret, final DoubleSupplier supplierAngle) {
    this.turret = turret;
    this.supplierAngle = supplierAngle;
    updateAngle = true;
    addRequirements(turret);
  }

  public MoveToAngle(final Turret turret, final DoubleSupplier supplierAngle, final boolean updateAngle) {
    this.turret = turret;
    this.supplierAngle = supplierAngle;
    this.updateAngle = updateAngle;
    addRequirements(turret);
  }

  @Override
  public void initialize() {
    turret.initEncoders();
  }

  @Override
  public void execute() {
    if (updateAngle){
      angle = turret.getAngle();
    }
    turret.moveToAngle(supplierAngle.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return Math.abs(turret.getEncoderPosition() - turret.convertAngleToEncoderUnits(supplierAngle.getAsDouble())) < TOLERANCE;
  }

  @Override
  public void end(boolean interrupted) {
    turret.stopMotor();
  }
}
