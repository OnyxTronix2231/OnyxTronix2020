package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.BALL_STOPPER_DELAY;
import static robot.crossSubsystem.CrossSubsystemConstants.TIME_BETWEEN_BALLS;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.commands.MoveBallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByBallStopperTriggerWithoutWaitingForLoader extends ParallelCommandGroup {

  public MoveConveyorsByBallStopperTriggerWithoutWaitingForLoader(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                                                  final StorageConveyor storageConveyor, final robot.ballStopper.BallStopper ballStopper,
                                                                  final DoubleSupplier velocitySupplier,
                                                                  final DoubleSupplier storageSpeedSupplier,
                                                                  final DoubleSupplier ballStopperSpeedSupplier) {
    super(sequence(new WaitUntilShooterVelocityOnTarget(shooter),
        parallel(
            new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
            new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier, BALL_STOPPER_DELAY))
            .withTimeout(TIME_BETWEEN_BALLS)));
  }
}
