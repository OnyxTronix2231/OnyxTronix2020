package robot.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import onyxTronix.UniqueTriggerCache;
import robot.drivetrain.commands.MoveToPose;

public class DriveTrainOi {
  public DriveTrainOi(final DriveTrain driveTrain, final UniqueTriggerCache driveJoystickButtonCache) {
    final OnyxTrajectoryGenerator trajectoryGenerator = new OnyxTrajectoryGenerator();

    final Trigger driveToPoseButton = driveJoystickButtonCache.createJoystickTrigger(XboxController.Button.kA.value);
    driveToPoseButton.whenActive(new MoveToPose(driveTrain, trajectoryGenerator,
        new Pose2d(1, 0, Rotation2d.fromDegrees(0))));
  }
}
