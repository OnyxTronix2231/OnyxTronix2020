package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button.*;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.climber.commands.Climb;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.CloseClimberPistons;
import robot.climber.commands.OpenClimberPistons;

public class ClimberOi {

  public ClimberOi(final UniqueButtonCache driverJoystickButtonCache,
                   final UniqueAxisCache driverJoystickAxisCache, final Climber climber) {
    final Trigger openClimberButton = driverJoystickButtonCache.createJoystickTrigger(kStart.value);
    openClimberButton.whenActive(new OpenClimberPistons(climber));

    final Trigger climbButton = driverJoystickButtonCache.createJoystickTrigger(kBack.value);
    climbButton.whenActive(new Climb(climber));
  }
}
