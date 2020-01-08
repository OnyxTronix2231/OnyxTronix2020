package robot.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootBySpeed;

public class ShooterOi {

    public ShooterOi(final UniqueTriggerCache buttonsJoystickAxisCache, final Shooter shooter) {
        final Trigger shootBySpeedButton = buttonsJoystickAxisCache.createJoystickTrigger(XboxController.Button.kA.value);
        shootBySpeedButton.whileActiveContinuous(new ShootBySpeed(shooter, () -> Shooter.SPEED));
    }
}
