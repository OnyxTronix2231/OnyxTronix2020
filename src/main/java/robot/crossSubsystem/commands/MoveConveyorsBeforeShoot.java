package robot.crossSubsystem.commands;

import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;
import static robot.loaderConveyor.LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class MoveConveyorsBeforeShoot extends SequentialCommandGroup {

  public MoveConveyorsBeforeShoot(final LoaderConveyor loaderConveyor, final BallStopper ballStopper,
                                  final StorageConveyor storageConveyor) {
    super(
        new WaitCommand(0.2).raceWith(
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX)),
        CommandGroupBase.race(new WaitCommand(0.3),
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX),
            new MoveBallStopperBySpeed(ballStopper, () -> -PERCENTAGE_OUTPUT),
            new MoveStorageConveyorBySpeed(storageConveyor, () -> StorageConveyorConstants.PERCENTAGE_OUTPUT)
        ));
  }
}
