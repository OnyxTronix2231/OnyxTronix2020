package robot.crossSubsystem.commands;

import robot.ballStopper.BallStopper;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.storageConveyor.StorageConveyor;

import java.util.function.DoubleSupplier;

public class ShootWithLoaderConveyorByDistance extends ShootWithLoaderConveyorTrigger {
  public ShootWithLoaderConveyorByDistance(Shooter shooter, LoaderConveyor loaderConveyor,
                                           StorageConveyor storageConveyor, BallStopper ballStopper,
                                           DoubleSupplier distanceSupplier, DoubleSupplier storageSpeedSupplier,
                                           DoubleSupplier ballStopperSpeedSupplier) {
    super(shooter, loaderConveyor, storageConveyor, ballStopper,
        () -> shooter.distanceToVelocity(distanceSupplier.getAsDouble()),
        storageSpeedSupplier, ballStopperSpeedSupplier);
  }
}
