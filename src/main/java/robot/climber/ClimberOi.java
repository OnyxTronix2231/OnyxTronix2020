package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.ClosePistons;
import robot.climber.commands.OpenPistons;

public class ClimberOi {

  public ClimberOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                   final UniqueAxisCache buttonJoystickAxisCache, final Climber climber) {
    final JoystickAxis climbBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kRightY.value);
    climbBySpeedAxis.whileActiveContinuous(new ClimbBySpeed(climber, climbBySpeedAxis::getRawAxis));

    final Trigger openDoubleSolenoidsButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kB.value);
    openDoubleSolenoidsButton.whenActive(new OpenPistons(climber));

    final Trigger closeDoubleSolenoidsButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kX.value);
    closeDoubleSolenoidsButton.whenActive(new ClosePistons(climber));
  }
}