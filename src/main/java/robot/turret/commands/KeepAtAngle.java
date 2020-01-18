package robot.turret.commands;

import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class KeepAtAngle extends MoveToAngleAndKeep{
  public KeepAtAngle(Turret turret, DoubleSupplier supplierAngle) {
    super(turret, supplierAngle);
  }

  @Override
  public void execute() {
    angle = supplierAngle.getAsDouble();
    super.execute();
  }
}
