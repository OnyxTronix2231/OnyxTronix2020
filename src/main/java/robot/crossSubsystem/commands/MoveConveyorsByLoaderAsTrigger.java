package robot.crossSubsystem.commands;

import static robot.crossSubsystem.CrossSubsystemConstants.WAIT_FOR_VELOCITY;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.shooter.Shooter;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;

import java.util.function.DoubleSupplier;

public class MoveConveyorsByLoaderAsTrigger extends ParallelCommandGroup {

  public MoveConveyorsByLoaderAsTrigger(final Shooter shooter, final LoaderConveyor loaderConveyor,
                                        final StorageConveyor storageConveyor, final BallStopper ballStopper,
                                        final DoubleSupplier loaderSpeed,
                                        final DoubleSupplier storageSpeedSupplier,
                                        final DoubleSupplier ballStopperSpeedSupplier) {
      super(sequence(new MoveLoaderConveyorBySpeed(loaderConveyor, () -> LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX)
          .withTimeout(0.3), new MoveConveyorsUntilBallInLoader(loaderConveyor, ballStopper, storageConveyor,
          LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX, StorageConveyorConstants.PERCENTAGE_OUTPUT,
          BallStopperConstants.PERCENTAGE_OUTPUT)));
  }
}
