package robot.crossSubsystem;

import static robot.ballStopper.BallStopperConstants.DELAY;
import static robot.ballStopper.BallStopperConstants.MOVE_STOPPER_BACK;
import static robot.ballStopper.BallStopperConstants.PERCENTAGE_OUTPUT;
import static robot.loaderConveyor.LoaderConveyorConstants.MOVE_LOADER_BACK;
import static robot.loaderConveyor.LoaderConveyorConstants.PERCENTAGE_OUTPUT_MAX;
import static robot.storageConveyor.StorageConveyorConstants.MOVE_STORAGE_BACK;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class ConveyorsOi {

  public ConveyorsOi(final UniqueButtonCache driveJoystickButtonCache, final LoaderConveyor loaderConveyor,
                     final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    final Trigger moveConveyorsBack = driveJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kX.value);
    moveConveyorsBack.whileActiveContinuous(new SequentialCommandGroup(
        new WaitCommand(0.2).raceWith(
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX)),
        CommandGroupBase.race(new WaitCommand(0.3),
            new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX),
            new MoveBallStopperBySpeed(ballStopper, () -> -PERCENTAGE_OUTPUT, DELAY),
            new MoveStorageConveyorBySpeed(storageConveyor, () -> StorageConveyorConstants.PERCENTAGE_OUTPUT)
        ),
        CommandGroupBase.parallel(
            CommandGroupBase.sequence(
                CommandGroupBase.race(
                    new WaitCommand(0.2),
                    new MoveLoaderConveyorBySpeed(loaderConveyor, () -> PERCENTAGE_OUTPUT_MAX)
                ),
                new MoveLoaderConveyorBySpeed(loaderConveyor, () -> 0.6)
            )
            ,
            new MoveBallStopperBySpeed(ballStopper, () -> PERCENTAGE_OUTPUT, DELAY),
            new MoveStorageConveyorBySpeed(storageConveyor, () -> StorageConveyorConstants.PERCENTAGE_OUTPUT)
        )
    ));
  }
}
