package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.TIME_BETWEEN_BALLS;
import static robot.crossSubsystem.CrossSubsystemConstants.WAIT_FOR_VELOCITY;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.WaitUntilLoaderVelocityOnTarget;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByBallStopperTrigger extends ParallelCommandGroup {

  public MoveConveyorsByBallStopperTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                           final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                           final DoubleSupplier storageSpeedSupplier,
                                           final DoubleSupplier ballStopperSpeedSupplier) {
    super(
        sequence(
            new WaitUntilShooterVelocityOnTarget(shooter, WAIT_FOR_VELOCITY),
            new WaitUntilLoaderVelocityOnTarget(loaderConveyor, WAIT_FOR_VELOCITY),
            parallel(
                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier))
                .withTimeout(TIME_BETWEEN_BALLS)));
  }
}
