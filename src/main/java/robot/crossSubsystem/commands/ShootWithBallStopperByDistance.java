package robot.crossSubsystem.commands;

import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.storageConveyor.StorageConveyor;

import java.util.function.DoubleSupplier;

public class ShootWithBallStopperByDistance extends ShootWithBallStopperTrigger {
  public ShootWithBallStopperByDistance(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor,
                                        final BallStopper ballStopper,
                                        final DoubleSupplier distanceSupplier,
                                        final DoubleSupplier storageSpeedSupplier,
                                        final DoubleSupplier ballStopperSpeedSupplier) {
    super(shooter, loaderConveyor, storageConveyor, ballStopper,
        () -> shooter.distanceToVelocity(distanceSupplier.getAsDouble()),
        storageSpeedSupplier, ballStopperSpeedSupplier);
  }
}
