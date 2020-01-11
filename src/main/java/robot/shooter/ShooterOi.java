package robot.shooter;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.Axis;
import onyxTronix.UniqueTriggerCache;

public class ShooterOi {

  public ShooterOi(final UniqueTriggerCache buttonsJoystickAxisCache, final Shooter shooter) {
    final Trigger shootBySpeedButton = buttonsJoystickAxisCache.createJoystickTrigger(Axis.kLeftY.value);
  }
}
