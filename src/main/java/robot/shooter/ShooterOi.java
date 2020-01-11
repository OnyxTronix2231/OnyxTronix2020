package robot.shooter;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.Axis;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueTriggerCache buttonJoystickAxisCache, final Shooter shooter){
    final Trigger shootBySpeedButton = buttonJoystickAxisCache.createJoystickTrigger(Axis.kLeftY.value);
    shootBySpeedButton.whileActiveContinuous(new ShootByPercentOutput(shooter, () -> ShooterConstants.SPEED));
  }
}
