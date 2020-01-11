package robot.shooter;

import static robot.shooter.ShooterConstants.SPEED;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.Axis;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueTriggerCache buttonsJoystickAxisCache, final Shooter shooter) {
    final Trigger shootBySpeedButton = buttonsJoystickAxisCache.createJoystickTrigger(Axis.kLeftY.value);
    shootBySpeedButton.whileActiveContinuous(new ShootByPercentOutput(shooter, () -> SPEED));
  }
}
