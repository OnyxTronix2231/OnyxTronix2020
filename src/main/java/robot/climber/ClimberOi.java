package robot.climber;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.Climb;

import static edu.wpi.first.wpilibj.XboxController.Button;

public class ClimberOi {

    public ClimberOi(final UniqueTriggerCache buttonsJoystickAxisCache, final Climber climber) {
        final Trigger climbButton = buttonsJoystickAxisCache.createJoystickTrigger(Button.kA.value);
        climbButton.whileActiveOnce(new Climb(climber, () -> ClimberConstants.SPEED));
    }
}
