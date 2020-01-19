package robot.crossSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.BallStopper.BallStopper;
import robot.BallStopper.Commands.MoveBallStopperBySpeed;
import robot.LoaderConveyor.LoaderConveyor;
import robot.LoaderConveyor.commands.MoveLoaderByVelocity;
import robot.LoaderConveyor.commands.WaitUntilLoaderVelocityOnTarget;
import robot.StorageConveyor.StorageConveyor;
import robot.StorageConveyor.commands.MoveStorageConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.ShootByVelocity;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;

import java.util.function.DoubleSupplier;

public class ShootWithBallStopperTrigger extends ParallelCommandGroup {

    public ShootWithBallStopperTrigger(Shooter shooter, LoaderConveyor loaderConveyor,
                                       StorageConveyor storageConveyor, BallStopper ballStopper,
                                       DoubleSupplier velocitySupplier, DoubleSupplier storageSpeedSupplier,
                                       DoubleSupplier ballStopperSpeedSupplier) {
        super(
                new ShootByVelocity(shooter, velocitySupplier),
                new MoveLoaderByVelocity(loaderConveyor, velocitySupplier),
                new SequentialCommandGroup(
                        new WaitUntilShooterVelocityOnTarget(shooter, velocitySupplier),
                        new WaitUntilLoaderVelocityOnTarget(loaderConveyor, velocitySupplier),
                        new ParallelCommandGroup (
                                new MoveStorageConveyorBySpeed(storageConveyor, storageSpeedSupplier),
                                new MoveBallStopperBySpeed(ballStopper, ballStopperSpeedSupplier))));
    }
}
