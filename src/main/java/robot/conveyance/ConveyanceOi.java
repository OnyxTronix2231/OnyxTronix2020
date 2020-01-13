package robot.conveyance;

import static edu.wpi.first.wpilibj.XboxController.Button;

import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.conveyance.commands.MoveStorageConveyorBySpeed;

public class ConveyanceOi {

  public ConveyanceOi(final UniqueAxisCache buttonsJoystickAxisCache, final StorageConveyor storageConveyor) {
    final JoystickAxis moveStorageBySpeedAxis =
        buttonsJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftX.value);
    moveStorageBySpeedAxis.whileActiveContinuous(new MoveStorageConveyorBySpeed(
        storageConveyor, moveStorageBySpeedAxis::getRawAxis));
  }
}
