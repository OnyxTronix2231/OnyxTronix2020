package robot.shooter;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueTriggerCache buttonsJoystickButtonCache, final Shooter shooter) {
    final Trigger shootBySpeedButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
    shootBySpeedButton.whileActiveContinuous(new ShootByPercentOutput(shooter, () -> ShooterConstants.SPEED));
  }
}
