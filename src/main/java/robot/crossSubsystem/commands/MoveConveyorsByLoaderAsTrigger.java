package robot.crossSubsystem.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.shooter.commands.WaitUntilShooterVelocityIsntOnTarget;
import robot.shooter.commands.WaitUntilShooterVelocityOnTarget;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByLoaderAsTrigger extends SequentialCommandGroup {

  public MoveConveyorsByLoaderAsTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final DoubleSupplier loaderSpeed,
                                        final DoubleSupplier storageSpeedSupplier,
                                        final DoubleSupplier ballStopperSpeedSupplier) {
    super(deadline(new WaitUntilShooterVelocityIsntOnTarget(shooter, 0.1),
            sequence(new WaitUntilShooterVelocityOnTarget(shooter, 0),
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX).
                withTimeout(0.3))),
        deadline(
            new WaitUntilShooterVelocityOnTarget(shooter, 0.1),
            new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper, storageConveyor,
                LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX, StorageConveyorConstants.PERCENTAGE_OUTPUT,
                BallStopperConstants.PERCENTAGE_OUTPUT)));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()){
      initialize();
    }
    return false;
  }
}
