package robot.conveyance;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.TransportToShooter.TransportToShooterConstants;
import robot.conveyance.commands.MoveBallConveyanceBySpeed;

import static edu.wpi.first.wpilibj.XboxController.Button;


public class ConveyanceOi {

    public ConveyanceOi(final Conveyance conveyance, final UniqueTriggerCache buttonJoystickAxisCache) {
        final Trigger moveSecondaryConveyanceBySpeed = buttonJoystickAxisCache.createJoystickTrigger(Button.kB.value);
        moveSecondaryConveyanceBySpeed.whileActiveContinuous(new MoveBallConveyanceBySpeed(conveyance, ()-> TransportToShooterConstants.CONVEYANCE_SPEED));
    }
}
