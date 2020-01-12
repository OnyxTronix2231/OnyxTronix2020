package robot.conveyance;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.conveyance.commands.MoveBallConveyanceBySpeed;


public class ConveyorOi {

  public ConveyorOi(final StorageConveyor conveyance, final UniqueAxisCache buttonJoystickAxisCache) {
    final JoystickAxis moveSecondaryConveyanceBySpeed = buttonJoystickAxisCache.createJoystickTrigger(Button.kB.value);
    moveSecondaryConveyanceBySpeed.whileActiveContinuous(new MoveBallConveyanceBySpeed(conveyance,
        () -> ConveyorConstants.CONVEYOR_SPEED));
  }
}
