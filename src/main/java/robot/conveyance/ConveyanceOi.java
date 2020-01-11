package robot.conveyance;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.conveyance.commands.MoveBallConveyanceBySpeed;


public class ConveyanceOi {

  public ConveyanceOi(final Conveyance conveyance, final UniqueTriggerCache buttonJoystickAxisCache){

    final Trigger moveSecondaryConveyanceBySpeed = buttonJoystickAxisCache.createJoystickTrigger(Button.kB.value);
    moveSecondaryConveyanceBySpeed.whileActiveContinuous(new MoveBallConveyanceBySpeed(conveyance ,
        () -> ConveyanceConstants.CONVEYANCE_SPEED));

  }
}
