package robot.crossSubsystem;

import static robot.ballStopper.BallStopperConstants.*;
import static robot.loaderConveyor.LoaderConveyorConstants.*;
import static robot.storageConveyor.StorageConveyorConstants.MOVE_STORAGE_BACK;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.ballStopper.commands.MoveBallStopperBySpeed;
import robot.crossSubsystem.commands.MoveAllConveyors;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.loaderConveyor.commands.MoveLoaderConveyorBySpeed;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class SmartConveyorsOi {

  public SmartConveyorsOi(final UniqueButtonCache driveJoystickButtonCache, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor, final BallStopper ballStopper){
    final Trigger moveConveyorsBack = driveJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kX.value);
    moveConveyorsBack.whileActiveContinuous(new ParallelCommandGroup(
        new MoveLoaderConveyorBySpeed(loaderConveyor, () -> MOVE_LOADER_BACK),
        new MoveBallStopperBySpeed(ballStopper, () -> MOVE_STOPPER_BACK, DELAY),
        new MoveStorageConveyorBySpeed(storageConveyor, () -> MOVE_STORAGE_BACK).withTimeout(0.2)
    ));
  }
}
