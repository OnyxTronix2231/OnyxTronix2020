package robot.storageConveyor;

import static robot.storageConveyor.StorageConveyorConstants.PERCENTAGE_OUTPUT;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class TestingStorageConveyorOi {

  public TestingStorageConveyorOi(final StorageConveyor storageConveyor, final UniqueButtonCache driveJoystickButtonCache) {
//    final JoystickButton moveStorageConveyorBySpeedButton =
//        driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kX.value);
//    moveStorageConveyorBySpeedButton.whileHeld(new MoveStorageConveyorBySpeed(storageConveyor, () -> PERCENTAGE_OUTPUT));
  }
}