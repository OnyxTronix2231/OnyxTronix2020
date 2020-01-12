package robot.shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueTriggerCache;
import robot.shooter.commands.ShootByPercentOutput;

public class ShooterOi {

  public ShooterOi(final UniqueTriggerCache buttonJoystickAxisCache, final Shooter shooter){
    final Trigger shootBySpeedAxis = buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    shootBySpeedAxis.whileActiveContinuous(new ShootByPercentOutput(shooter,
        () -> new Joystick(0).getRawAxis(JoystickAxis.AxisMap.kLeftY.value)));
  }
}
