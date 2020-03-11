package robot.crossSubsystem.commands;

import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;
import static robot.loaderConveyor.LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class MoveLoaderToShoot extends SequentialCommandGroup {

  public MoveLoaderToShoot(final LoaderConveyor loaderConveyor, final BallStopper ballStopper,
                           final StorageConveyor storageConveyor) {

    parallel(
        sequence(
            race(
                new WaitCommand(0.2),
                new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX)),
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX)),
        new MoveBallStopperBySpeed(ballStopper, () -> PERCENTAGE_OUTPUT),
        new MoveStorageConveyorBySpeed(storageConveyor, () -> StorageConveyorConstants.PERCENTAGE_OUTPUT));
  }

  @Override
  public boolean isFinished() {
    if (super.isFinished()) {
      initialize();
    }
    return false;
  }
}
