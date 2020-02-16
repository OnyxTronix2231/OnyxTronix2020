package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class ShootWithLoaderConveyorTrigger extends ParallelCommandGroup {

  public ShootWithLoaderConveyorTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final DoubleSupplier velocitySupplier, final DoubleSupplier storageSpeedSupplier,
                                        final DoubleSupplier ballStopperSpeedSupplier) {
    super(new ShootByVelocity(shooter, velocitySupplier),
        new SequentialCommandGroup(
            new WaitUntilShooterVelocityOnTarget(shooter, velocitySupplier),
            new ParallelCommandGroup(
                new MoveLoaderByVelocity(loaderConveyor, velocitySupplier),
                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier))));
  }
}
