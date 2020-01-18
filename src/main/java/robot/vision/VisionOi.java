package robot.vision;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.DriveTrain;
import robot.turret.Turret;
import robot.vision.commands.CorrectTurretToTarget;
import robot.vision.limelight.Limelight;

public class VisionOi {

  public VisionOi(final UniqueTriggerCache buttonsJoystickButtonCache, final XboxController driveJoystick,
                  final Turret turret) {
    final Trigger moveByAngleButton =
        buttonsJoystickButtonCache.createJoystickTrigger(XboxController.Button.kB.value);
    Limelight limelight = Limelight.getInstance();
    moveByAngleButton.whenActive(new CorrectTurretToTarget(turret, limelight::getTarget));

}
}
