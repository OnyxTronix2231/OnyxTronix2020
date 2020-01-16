package robot.climber;

import static edu.wpi.first.wpilibj.XboxController.Button;
import static edu.wpi.first.wpilibj.XboxController.Button.*;
import static onyxTronix.JoystickAxis.*;
import static onyxTronix.JoystickAxis.AxisMap.*;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import onyxTronix.UniqueTriggerCache;
import robot.climber.commands.ClimbBySpeed;
import robot.climber.commands.ClosePistons;
import robot.climber.commands.OpenPistons;

public class ClimberOi {

  public ClimberOi(final UniqueButtonCache driverJoystickButtonCache,
                   final UniqueAxisCache driverJoystickAxisCache, final Climber climber) {
    final JoystickAxis climbBySpeedAxis =
        driverJoystickAxisCache.createJoystickTrigger(kRightY.value);
    climbBySpeedAxis.whileActiveContinuous(new ClimbBySpeed(climber, climbBySpeedAxis::getRawAxis));

    final Trigger openDoubleSolenoidsButton = driverJoystickButtonCache.createJoystickTrigger(kY.value);
    openDoubleSolenoidsButton.whenActive(new OpenPistons(climber));

    final Trigger closeDoubleSolenoidsButton = driverJoystickButtonCache.createJoystickTrigger(kX.value);
    closeDoubleSolenoidsButton.whenActive(new ClosePistons(climber));
  }
}