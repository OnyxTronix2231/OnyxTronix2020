package robot.conveyance;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.TransportToShooter.TransportToShooterConstants;
import robot.conveyance.commands.MoveBallConveyanceBySpeed;
import robot.conveyance.commands.MoveStorageConveyorBySpeed;

import static edu.wpi.first.wpilibj.XboxController.Button;


public class ConveyanceOi {

    public ConveyanceOi(final Conveyance conveyance, final UniqueAxisCache buttonJoystickAxisCache, final StorageConveyor storageConveyor) {
        final Trigger moveSecondaryConveyanceBySpeed = buttonJoystickAxisCache.createJoystickTrigger(Button.kB.value);
        moveSecondaryConveyanceBySpeed.whileActiveContinuous(new MoveBallConveyanceBySpeed(conveyance, ()-> TransportToShooterConstants.CONVEYANCE_SPEED));
        final JoystickAxis moveStorageBySpeedAxis =
                buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftX.value);
        moveStorageBySpeedAxis.whileActiveContinuous(new MoveStorageConveyorBySpeed(
                storageConveyor, moveStorageBySpeedAxis::getRawAxis));
    }
}
