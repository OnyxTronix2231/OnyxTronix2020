package robot.TransportToShooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.TransportToShooter.commands.MoveBallTransportToShooterBySpeed;
import robot.conveyance.commands.MoveBallConveyanceBySpeed;

public class TransportToShooterOi {

    public TransportToShooterOi(final TransportToShooter transportToShooter, final UniqueTriggerCache buttonJoystickAxisCache) {
        final Trigger moveTransportBySpeed = buttonJoystickAxisCache.createJoystickTrigger(XboxController.Button.kB.value);
        moveTransportBySpeed.whileActiveContinuous(new MoveBallTransportToShooterBySpeed(transportToShooter,
                () -> TransportToShooterConstants.CONVEYANCE_SPEED));
    }
}
