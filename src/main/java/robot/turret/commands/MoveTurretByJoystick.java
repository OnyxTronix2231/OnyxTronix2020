package robot.turret.commands;

import robot.turret.Turret;

import java.util.function.DoubleSupplier;

public class MoveTurretByJoystick extends MoveTurretByAngle {

  public MoveTurretByJoystick(final Turret turret, final DoubleSupplier percentSupplier ) {
    super(turret, () -> turret.getAngleRTR() + percentSupplier.getAsDouble());
  }
}
