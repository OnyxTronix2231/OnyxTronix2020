package robot.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.commands.RotateToAngleNavX;
import robot.turret.Turret;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                  final Turret turret, final DriveTrain driveTrain) {
    Trigger correctRobotToTargetTrigger = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStart.value);
    correctRobotToTargetTrigger.whenActive(new RotateToAngleNavX(driveTrain, () -> driveTrain.getNavXYaw() + 90));
  }
}
