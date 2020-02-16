package robot.drivetrain.commands;

import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.TrajectoryParamsA.RAMSETE_ZETA;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.OnyxTrajectoryGenerator;

import java.util.List;

public class MoveToPose extends OnyxRamseteCommand {
  public MoveToPose(final DriveTrain driveTrain, final OnyxTrajectoryGenerator trajectoryGenerator, final Pose2d finishPose) {
    super(() -> trajectoryGenerator.getTrajectoryFromPoseList(List.of(driveTrain.getPose(), finishPose),
        driveTrain.getComponents()), driveTrain::getPose, driveTrain::driveTrainVelocity, new RamseteController(
        RAMSETE_B, RAMSETE_ZETA), driveTrain.getComponents().getDriveKinematics(), driveTrain);
  }
}
