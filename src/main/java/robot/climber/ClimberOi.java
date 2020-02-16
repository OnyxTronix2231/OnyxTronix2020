package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button.kBack;
import static edu.wpi.first.wpilibj.XboxController.Button.kStart;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.CloseClimberPistons;
import robot.climber.commands.OpenClimberPistons;

public class ClimberOi {

  public ClimberOi(final UniqueButtonCache driverJoystickButtonCache,
                   final UniqueAxisCache driverJoystickAxisCache, final Climber climber) {
    final JoystickAxis climbUpBySpeedAxis =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kRightTrigger.value);
    climbUpBySpeedAxis.whileActiveContinuous(new ClimbBySpeed(climber, climbUpBySpeedAxis::getRawAxis));

    final JoystickAxis climbDownBySpeedAxis =
        driverJoystickAxisCache.createJoystickTrigger(XboxController.Axis.kLeftTrigger.value);
    climbUpBySpeedAxis.whileActiveContinuous(new ClimbBySpeed(climber,
        () -> -climbDownBySpeedAxis.getRawAxis()));

    final Trigger openClimberPistonsButton = driverJoystickButtonCache.createJoystickTrigger(kStart.value);
    openClimberPistonsButton.whenActive(new OpenClimberPistons(climber));

    final Trigger closeClimberPistonsButton = driverJoystickButtonCache.createJoystickTrigger(kBack.value);
    closeClimberPistonsButton.whenActive(new CloseClimberPistons(climber));
  }
}
