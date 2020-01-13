package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.MAX_SPEED_METERS_PER_SECOND;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.P_DRIVE_VEL;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_B;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.RAMSETE_ZETA;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.VOLTS;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.VOLT_SECONDS_PER_METER;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.VOLT_SECONDS_SQUARED_PER_METER;

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
      VOLTS,
      VOLT_SECONDS_PER_METER,
      VOLT_SECONDS_SQUARED_PER_METER);

  private final DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
      feedforward, DRIVE_KINEMATICS, 10);

  private final TrajectoryConfig config = new TrajectoryConfig(MAX_SPEED_METERS_PER_SECOND, MAX_ACCELERATION_METERS_PER_SECOND_SQUARED)
      .setKinematics(DRIVE_KINEMATICS).addConstraint(autoVoltageConstraint);

  private Trajectory getTrajectoryFromPoses(final Pose2d startPose, final Pose2d endPose) {
    return TrajectoryGenerator.generateTrajectory(startPose,
        List.of(new Translation2d(1, -1), new Translation2d(2, -1)), endPose, config);
  }

  public Command getAutonomousCommandFromPoses(final Pose2d startPose, final Pose2d endPose) {
    final Trajectory trajectory = getTrajectoryFromPoses(startPose, endPose);

    final RamseteCommand ramseteCommand = new RamseteCommand(trajectory, driveTrain::getPose,
        new RamseteController(RAMSETE_B, RAMSETE_ZETA),
        feedforward, DRIVE_KINEMATICS, driveTrain::getWheelSpeeds,
        new PIDController(P_DRIVE_VEL, 0, 0),
        new PIDController(P_DRIVE_VEL, 0, 0),
        driveTrain::tankDriveVolts, driveTrain);

    return ramseteCommand.andThen(() -> driveTrain.tankDriveVolts(0, 0));
  }
}
