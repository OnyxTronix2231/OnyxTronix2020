package robot.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.vision.commands.CorrectRobotToTarget;
import robot.vision.commands.CorrectTurretToTarget;
import robot.vision.limelight.Limelight;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache buttonsJoystickButtonCache,
                  final Turret turret, final DriveTrain driveTrain) {
    Trigger correctRobotToTargetTrigger = buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kStart.value);
    correctRobotToTargetTrigger.whenActive(new CorrectRobotToTarget(driveTrain, () -> new VisionTarget(
        Limelight.getInstance().getTarget(), driveTrain.getNavXYaw())));
  }
}
