package robot.drivetrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import java.util.List;

public class OnyxTrajectoryGenerator {
  private final DriveTrain driveTrain = new DriveTrain(new BasicDriveTrainComponents());

  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
      DriveTrainConstants.TRAJECTORY_PARAMS.VOLTS,
      DriveTrainConstants.TRAJECTORY_PARAMS.VOLT_SECONDS_PER_METER,
      DriveTrainConstants.TRAJECTORY_PARAMS.VOLT_SECONDS_SQUARED_PER_METER);

  private final DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
      feedforward,
      DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS, 10);

  private final TrajectoryConfig config = new TrajectoryConfig(
      DriveTrainConstants.TRAJECTORY_PARAMS.MAX_SPEED_METERS_PER_SECOND, DriveTrainConstants.TRAJECTORY_PARAMS.KAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
      .setKinematics(DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS).addConstraint(autoVoltageConstraint);

  public final Trajectory getTrajectoryFromPoses(final Pose2d startPose, final Pose2d endPose) {
    return TrajectoryGenerator.generateTrajectory(startPose,
        List.of(new Translation2d(1, -1), new Translation2d(2, -1)), endPose, config);
  }

  public final Command getAutonomousCommandFromPoses(final Pose2d startPose, final Pose2d endPose) {
    final Trajectory trajectory = getTrajectoryFromPoses(startPose, endPose);

    final RamseteCommand ramseteCommand = new RamseteCommand(trajectory, driveTrain::getPose,
        new RamseteController(DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_B, DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_ZETA),
        feedforward, DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS, driveTrain::getWheelSpeeds,
        new PIDController(DriveTrainConstants.TRAJECTORY_PARAMS.P_DRIVE_VEL, 0, 0),
        new PIDController(DriveTrainConstants.TRAJECTORY_PARAMS.P_DRIVE_VEL, 0, 0),
        driveTrain::tankDriveVolts,
        driveTrain);

    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }
}
