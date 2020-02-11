package robot.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.turret.Turret;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                  final Turret turret) {
    Trigger correctRobotToTargetTrigger = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStart.value);
  }
}
