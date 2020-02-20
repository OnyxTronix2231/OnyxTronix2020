package robot.turret.commands;

import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretByJoystick extends MoveTurretToAngleAndKeep {

  public MoveTurretByJoystick(final Turret turret, final DoubleSupplier percentSupplier) {
    super(turret, percentSupplier);
  }

  @Override
  public void initialize() {
    super.initialize();
    angle = turret.getAngleRTR();
  }

  @Override
  public void execute() {
    angle += supplierAngle.getAsDouble();
    super.execute();
  }
}
