package robot.yawControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.yawControl.commands.SetTurretState;

public class YawControlOi {
  public YawControlOi(final YawControl yawControl, final UniqueButtonCache buttonJoystickButtonCache,
                     final UniqueAxisCache buttonsJoystickAxisCache) {
    final JoystickButton button = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    final JoystickButton button2 = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperRight.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    final JoystickButton button3 = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.Homing));
  }
}

