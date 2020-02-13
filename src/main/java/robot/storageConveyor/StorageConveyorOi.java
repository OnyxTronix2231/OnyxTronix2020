package robot.storageConveyor;

import static robot.storageConveyor.StorageConveyorConstants.StorageConveyorComponentsA.ENGINE_SPEED;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.UniqueButtonCache;
import robot.storageConveyor.commands.MoveStorageConveyorBySpeed;

public class StorageConveyorOi {

  public StorageConveyorOi(final StorageConveyor storageConveyor, final UniqueButtonCache buttonsJoystickButtonCache){

    final JoystickButton moveStorageConveyorBySpeedButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kY.value);
    moveStorageConveyorBySpeedButton.whileHeld(new MoveStorageConveyorBySpeed(storageConveyor, () -> ENGINE_SPEED));
  }
}
