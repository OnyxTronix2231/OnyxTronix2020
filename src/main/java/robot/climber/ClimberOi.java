package robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.ClimbBySpeed;

import static edu.wpi.first.wpilibj.XboxController.Button;

public class ClimberOi {

    public ClimberOi(final UniqueTriggerCache buttonsJoystickButtonCache, final Climber climber) {
        final Trigger climbButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
        climbButton.whileActiveOnce(new ClimbBySpeed(climber, () -> ClimberConstants.SPEED));
    }
}
