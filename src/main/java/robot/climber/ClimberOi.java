package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.CloseDoubleSolenoid;
import robot.climber.commands.OpenDoubleSolenoid;

public class ClimberOi {

  public ClimberOi(final UniqueTriggerCache buttonsJoystickButtonCache, final Climber climber) {
    final Trigger climbButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
    climbButton.whileActiveOnce(new ClimbBySpeed(climber, () -> ClimberConstants.SPEED));

    final Trigger openSolenoidButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
    openSolenoidButton.whenActive(new OpenDoubleSolenoid(climber));

    final Trigger closeSolenoidButton = buttonsJoystickButtonCache.createJoystickTrigger(Button.kA.value);
    closeSolenoidButton.whenActive(new CloseDoubleSolenoid(climber));
  }
}
