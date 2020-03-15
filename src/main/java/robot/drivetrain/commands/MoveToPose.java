package robot.drivetrain.commands;

import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.DriveTrainComponentsA.TrajectoryParams.RAMSETE_ZETA;

import edu.wpi.first.wpilibj.controller.RamseteController;
import robot.drivetrain.DriveTrain;
import robot.drivetrain.Pose;

import java.util.List;

public class MoveToPose extends OnyxRamseteCommand {
  public MoveToPose(final DriveTrain driveTrain,
                    final Pose finishPose) {
    super(() -> driveTrain.getTrajectoryGenerator().getTrajectoryFromPoseList(List.of(driveTrain.getPose(),
        finishPose.getPose2d()),
        driveTrain.getComponents(), finishPose.isForward()),
        driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        driveTrain.getKinematics(),
        driveTrain::getWheelSpeeds,
        driveTrain::tankDriveVolts,
        driveTrain.getFeedForward(),
        driveTrain);
  }

  @Override
  public void initialize() {
    super.initialize();
    System.out.println("Start");
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    System.out.println("End");
  }
}
