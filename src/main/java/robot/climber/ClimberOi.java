package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.ClosePistons;
import robot.climber.commands.OpenPistons;

public class ClimberOi {

  public ClimberOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                   final UniqueTriggerCache buttonJoystickAxisCache, final Climber climber) {
    final JoystickAxis climbBySpeedAxis = (JoystickAxis)
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    climbBySpeedAxis.whileActiveContinuous(new ClimbBySpeed(climber, climbBySpeedAxis::getRawAxis));

    final Trigger openSolenoidButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kB.value);
    openSolenoidButton.whenActive(new OpenPistons(climber));

    final Trigger closeSolenoidButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kX.value);
    closeSolenoidButton.whenActive(new ClosePistons(climber));
  }
}
