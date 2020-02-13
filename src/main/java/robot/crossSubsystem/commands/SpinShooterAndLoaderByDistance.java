package robot.crossSubsystem.commands;

import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;

import java.util.function.DoubleSupplier;

public class SpinShooterAndLoaderByDistance extends SpinShooterAndLoader {
  public SpinShooterAndLoaderByDistance(Shooter shooter, LoaderConveyor loaderConveyor,
                                        DoubleSupplier distanceSupplier) {
    super(shooter, loaderConveyor, () -> shooter.distanceToVelocity(distanceSupplier.getAsDouble()));
  }
}
