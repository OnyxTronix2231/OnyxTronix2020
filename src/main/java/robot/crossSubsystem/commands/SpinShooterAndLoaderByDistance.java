package robot.crossSubsystem.commands;

import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class SpinShooterAndLoaderByDistance extends SpinShooterAndLoader {

  public SpinShooterAndLoaderByDistance(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final DoubleSupplier distanceSupplier) {
    super(shooter, loaderConveyor, () -> shooter.distanceToVelocity(distanceSupplier.getAsDouble()));
  }
}
