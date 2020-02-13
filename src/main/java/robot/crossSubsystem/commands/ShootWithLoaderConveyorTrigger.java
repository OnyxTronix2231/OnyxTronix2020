package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.Commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderByVelocity;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

import java.util.function.DoubleSupplier;

public class ShootWithLoaderConveyorTrigger extends ParallelCommandGroup {

  public ShootWithLoaderConveyorTrigger(Shooter shooter, LoaderConveyor loaderConveyor,
                                        StorageConveyor storageConveyor, BallStopper ballStopper,
                                        DoubleSupplier velocitySupplier, DoubleSupplier storageSpeedSupplier,
                                        DoubleSupplier ballStopperSpeedSupplier) {
    super(new ShootByVelocity(shooter, velocitySupplier),
        new SequentialCommandGroup(
            new WaitUntilShooterVelocityOnTarget(shooter, velocitySupplier),
            new ParallelCommandGroup(
                new MoveLoaderByVelocity(loaderConveyor, velocitySupplier),
                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier))));
  }
}
