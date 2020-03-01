package robot.drivetrain.commands;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_ZETA;

import edu.wpi.first.wpilibj.controller.RamseteController;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.OnyxTrajectoryGenerator;
import robot.drivetrain.Path;

public class MoveByPath extends OnyxRamseteCommand {
  public MoveByPath(final DriveTrain driveTrain,
                    final OnyxTrajectoryGenerator trajectoryGenerator,
                    final Path path) {
    super(() -> trajectoryGenerator.getTrajectoryFromPoseList(path.getPath(),
        driveTrain.getComponents()),
        path.getIsForward() ? driveTrain::getPose : driveTrain::getReversedPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        driveTrain.getKinematics(),
        driveTrain::getWheelSpeeds,
        (path.getIsForward() ? driveTrain::tankDriveVolts : driveTrain::tankDriveVoltsReverse),
        driveTrain.getFeedForward(),
        driveTrain);
  }
}
