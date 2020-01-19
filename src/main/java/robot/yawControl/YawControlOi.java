package robot.yawControl;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import onyxTronix.JoystickAxis;
import onyxTronix.UniqueAxisCache;
import onyxTronix.UniqueButtonCache;
import robot.yawControl.commands.ChangeAngleOffsetByPercent;
import robot.yawControl.commands.SetTurretState;

public class YawControlOi {
  public YawControlOi(YawControl yawControl, UniqueButtonCache buttonJoystickButtonCache,
                      UniqueAxisCache buttonsJoystickAxisCache) {
    JoystickButton button = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperLeft.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTF));

    button = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kBumperRight.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.RTR));

    button = buttonJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    button.whenPressed(new SetTurretState(yawControl, YawControl.TurretState.Homing));

    JoystickAxis axis = buttonsJoystickAxisCache.createJoystickTrigger(JoystickAxis.AxisMap.kRightX.value);
    axis.whileActiveContinuous(new ChangeAngleOffsetByPercent(yawControl, axis::getRawAxis));
  }
}

