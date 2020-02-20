package robot.shooter.commands;

import static robot.shooter.ShooterConstants.*;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class ShootByDistance extends ShootByVelocity {

  public ShootByDistance(final Shooter shooter, final DoubleSupplier distanceSupplier) {
    super(shooter, () -> shooter.distanceToVelocity(distanceSupplier.getAsDouble()));
  }
}
