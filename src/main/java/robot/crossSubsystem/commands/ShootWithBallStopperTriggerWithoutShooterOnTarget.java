package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.Commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderByVelocityWhitoutEnd;
import robot.loaderConveyor.commands.WaitUntilLoaderVelocityOnTarget;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocityWithoutEnd;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class ShootWithBallStopperTriggerWithoutShooterOnTarget extends ParallelCommandGroup {

  public ShootWithBallStopperTriggerWithoutShooterOnTarget(Shooter shooter, LoaderConveyor loaderConveyor,
                                                           StorageConveyor storageConveyor, BallStopper ballStopper,
                                                           DoubleSupplier velocitySupplier,
                                                           DoubleSupplier storageSpeedSupplier,
                                                           DoubleSupplier ballStopperSpeedSupplier) {
    super(deadline(
        sequence(
            new WaitUntilLoaderVelocityOnTarget(loaderConveyor, velocitySupplier),
            parallel(
                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier)).withTimeout(0.5))),
        new ShootByVelocityWithoutEnd(shooter, velocitySupplier),
        new MoveLoaderByVelocityWhitoutEnd(loaderConveyor, velocitySupplier));
  }
}

