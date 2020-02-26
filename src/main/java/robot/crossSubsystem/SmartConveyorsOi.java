package robot.crossSubsystem;

import static robot.ballStopper.BallStopperConstants.*;
import static robot.loaderConveyor.LoaderConveyorConstants.*;
import static robot.storageConveyor.StorageConveyorConstants.MOVE_STORAGE_BACK;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.ballCollector.BallCollector;
import robot.ballStopper.BallStopper;
import robot.ballStopper.BallStopperConstants;
import robot.crossSubsystem.commands.MoveAllConveyors;
import robot.loaderConveyor.LoaderConveyor;
import robot.loaderConveyor.LoaderConveyorConstants;
import robot.storageConveyor.StorageConveyor;
import robot.storageConveyor.StorageConveyorConstants;

public class SmartConveyorsOi {

  public SmartConveyorsOi(final UniqueButtonCache driveJoystickButtonCache, final LoaderConveyor loaderConveyor,
                          final StorageConveyor storageConveyor, final BallStopper ballStopper){
    final Trigger moveConveyorsBack = driveJoystickButtonCache.createJoystickTrigger(
        XboxController.Button.kX.value);
    moveConveyorsBack.whileActiveContinuous(new MoveAllConveyors(loaderConveyor, ballStopper, storageConveyor,
        MOVE_LOADER_BACK, MOVE_STORAGE_BACK, MOVE_STOPPER_BACK));
  }
}
