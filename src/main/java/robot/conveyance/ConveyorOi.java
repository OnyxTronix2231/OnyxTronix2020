package robot.conveyance;

import static edu.wpi.first.wpilibj.XboxController.Button;

import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import robot.conveyance.commands.MoveStorageConveyorBySpeed;


public class ConveyorOi {

  public ConveyorOi(final StorageConveyor conveyance, final UniqueAxisCache buttonJoystickAxisCache) {
    final JoystickAxis moveSecondaryConveyanceBySpeed = buttonJoystickAxisCache.createJoystickTrigger(Button.kB.value);
    moveSecondaryConveyanceBySpeed.whileActiveContinuous(new MoveStorageConveyorBySpeed(conveyance,
        () -> ConveyorConstants.CONVEYOR_SPEED));
  }
}
