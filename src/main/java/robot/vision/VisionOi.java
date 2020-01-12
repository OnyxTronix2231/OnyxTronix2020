package robot.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.DriveTrain;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache driveJoystickButtonCache, final XboxController driveJoystick,
                  final DriveTrain driveTrain) {
     Trigger correctByVisionButton = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
     correctByVisionButton.whileActiveOnce(new CorrectByVision(driveTrain, driveJoystick));
  }
}
