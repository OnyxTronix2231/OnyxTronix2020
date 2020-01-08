package robot.shooter;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootBySpeed;

public class ShooterOi {

    public ShooterOi(final UniqueTriggerCache buttonsJoystickAxisCache, final Shooter shooter) {
        final Trigger shootBySpeedButton = buttonsJoystickAxisCache.createJoystickTrigger(Button.kA.value);
        shootBySpeedButton.whileActiveContinuous(new ShootBySpeed(shooter, () -> ShooterConstants.SPEED));
    }
}
