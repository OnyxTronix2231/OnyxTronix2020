package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.WAIT_FOR_VELOCITY;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByLoaderAsTrigger extends ParallelCommandGroup {

  public MoveConveyorsByLoaderAsTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final DoubleSupplier loaderSpeed,
                                        final DoubleSupplier storageSpeedSupplier,
                                        final DoubleSupplier ballStopperSpeedSupplier) {
    super(sequence(
        new WaitUntilShooterVelocityOnTarget(shooter, WAIT_FOR_VELOCITY),
        sequence(
            new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed).withTimeout(0.2) ,
            new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier).withTimeout(0.2)),
            parallel(
                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed).withTimeout(0.2),
                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier).withTimeout(0.2))));
  }
}
