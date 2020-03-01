package robot.drivetrain.commands;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_ZETA;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.drivetrain.DriveTrain;

import java.util.List;

public class MoveToPose extends OnyxRamseteCommand {
  public MoveToPose(final DriveTrain driveTrain,
                    final Pose2d finishPose) {
    super(() -> driveTrain.getTrajectoryGenerator().getTrajectoryFromPoseList(List.of(driveTrain.getPose(), finishPose),
        driveTrain.getComponents()),
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        driveTrain.getKinematics(),
        driveTrain::getWheelSpeeds,
        driveTrain::tankDriveVolts,
        driveTrain.getFeedForward(),
        driveTrain);
  }
}
