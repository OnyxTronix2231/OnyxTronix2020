package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_DELAY;
import static robot.crossSubsystem.CrossSubsystemConstants.DELAY_AFTER_SHOOT;
import static robot.crossSubsystem.CrossSubsystemConstants.LOADER_DELAY;
import static robot.crossSubsystem.CrossSubsystemConstants.TIME_BETWEEN_BALLS;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
        new WaitCommand(DELAY_AFTER_SHOOT),
        new WaitUntilShooterVelocityOnTarget(shooter),
        new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed).withTimeout(LOADER_DELAY),
        parallel(
            new MoveLoaderConveyorBySpeed(loaderConveyor, loaderSpeed),
            new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
            new MoveBallStopperBySpeed(ballStopper,ballStopperSpeedSupplier, BALL_STOPPER_DELAY )
            .withTimeout(TIME_BETWEEN_BALLS))));
  }
}
