package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.Commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderByVelocityWithoutEnd;
import robot.loaderConveyor.commands.WaitUntilLoaderVelocityOnTarget;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocityWithoutEnd;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class ShootWithBallStopperTrigger extends ParallelCommandGroup {

  public ShootWithBallStopperTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                     final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                     final DoubleSupplier velocitySupplier, final DoubleSupplier storageSpeedSupplier,
                                     final DoubleSupplier ballStopperSpeedSupplier) {
    super(
        deadline(
            sequence(
                new WaitUntilShooterVelocityOnTarget(shooter,
                    velocitySupplier),
                new WaitUntilLoaderVelocityOnTarget(loaderConveyor, velocitySupplier),
                parallel(
                    new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                    new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier)).withTimeout(0.5))),
        new ShootByVelocityWithoutEnd(shooter, velocitySupplier),
        new MoveLoaderByVelocityWithoutEnd(loaderConveyor, velocitySupplier));
  }
}
