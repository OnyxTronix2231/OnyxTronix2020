package robot.turret.commands;

import static robot.turret.TurretConstants.TOLERANCE;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveToAngle extends MoveToAngleAndKeep {

  public MoveToAngle(final Turret turret, final DoubleSupplier supplierAngle) {
    super(turret, supplierAngle);
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble();
    super.execute();
  }

  @Override
  public boolean isFinished() {
    return turret.isOnTarget();
  }
}
