package robot.turret;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueTriggerCache;
import robot.turret.commands.MoveBySpeed;
import robot.turret.commands.MoveToAngle;

public class TurretOi {

  public TurretOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                   final UniqueAxisCache buttonJoystickAxisCache, final Turret turret) {
    final JoystickAxis moveBySpeedAxis =
        buttonJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kLeftY.value);
    moveBySpeedAxis.whileActiveContinuous(new MoveBySpeed(turret, moveBySpeedAxis::getRawAxis));

    final Trigger moveByAngleButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
    moveByAngleButton.whenActive(new MoveToAngle(turret, () -> 360));

  }
}
