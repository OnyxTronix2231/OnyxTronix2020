package robot.turret;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.turret.commands.MoveBySpeed;
import robot.turret.commands.MoveToAngle;

public class TurretOi {

  public TurretOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                   final UniqueAxisCache buttonJoystickAxisCache, final Turret turret) {
    final JoystickAxis climbBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    climbBySpeedAxis.whileActiveContinuous(new MoveBySpeed(turret, climbBySpeedAxis::getRawAxis));

    final Trigger moveToAngleButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
    moveToAngleButton.whenActive(new MoveToAngle(turret, 20));

  }
}
