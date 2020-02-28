package robot.crossSubsystem;

import static robot.ballStopper.BallStopperConstants.DELAY;
import static robot.ballStopper.BallStopperConstants.MOVE_STOPPER_BACK;
import static robot.loaderConveyor.LoaderConveyorConstants.MOVE_LOADER_BACK;
import static robot.storageConveyor.StorageConveyorConstants.MOVE_STORAGE_BACK;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueButtonCache;
import robot.ballStopper.BallStopper;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class ConveyorsOi {

  public ConveyorsOi(final UniqueButtonCache driveJoystickButtonCache, final LoaderConveyor loaderConveyor,
                     final StorageConveyor storageConveyor, final BallStopper ballStopper) {
    final Trigger moveConveyorsBack = driveJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kX.value);
    moveConveyorsBack.whileActiveContinuous(new ParallelCommandGroup(
        new MoveLoaderConveyorBySpeed(loaderConveyor, () -> MOVE_LOADER_BACK),
        new MoveBallStopperBySpeed(ballStopper, () -> MOVE_STOPPER_BACK, DELAY),
        new MoveStorageConveyorBySpeed(storageConveyor, () -> MOVE_STORAGE_BACK).withTimeout(0.2)
    ));
  }
}
