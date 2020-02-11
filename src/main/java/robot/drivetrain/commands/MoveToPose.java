package robot.drivetrain.commands;

import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_ZETA;

import edu.wpi.first.wpilibj.controller.RamseteController;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.OnyxTrajectoryGenerator;

import java.util.List;

public class MoveToPose extends OnyxRamseteCommand {
  public MoveToPose(final DriveTrain driveTrain, final OnyxTrajectoryGenerator trajectoryGenerator) {
    super(() -> trajectoryGenerator.getTrajectoryFromPoseList(List.of(driveTrain.getPose(), driveTrain.getTargetPose())),
        driveTrain::getPose, driveTrain::driveTrainVelocity, new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        DRIVE_KINEMATICS, driveTrain);
  }
}
