package robot.turret.commands;

import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretToAngle extends MoveTurretToAngleAndKeep {

  public MoveTurretToAngle(final Turret turret, final DoubleSupplier angleSupplier) {
    super(turret, angleSupplier);
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
