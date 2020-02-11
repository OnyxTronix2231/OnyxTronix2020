package robot.drivetrain;

import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.DRIVE_KINEMATICS;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.FEED_FORWARD;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.MAX_ACCELERATION_METERS_PER_SECOND_SQUARED;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.MAX_SPEED_METERS_PER_SECOND;
import static robot.drivetrain.DriveTrainConstants.TRAJECTORY_PARAMS.MAX_VOLTAGE;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

import java.util.List;

public class OnyxTrajectoryGenerator {
  private static final DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT =
      new DifferentialDriveVoltageConstraint(FEED_FORWARD, DRIVE_KINEMATICS, MAX_VOLTAGE);

  private static final TrajectoryConfig CONFIG = new TrajectoryConfig(MAX_SPEED_METERS_PER_SECOND,
      MAX_ACCELERATION_METERS_PER_SECOND_SQUARED).setKinematics(DRIVE_KINEMATICS).addConstraint(AUTO_VOLTAGE_CONSTRAINT);

  public Trajectory getTrajectoryFromPoseList(final List<Pose2d> poses) {
    try {
      return TrajectoryGenerator.generateTrajectory(poses, CONFIG);
    } catch (final Exception e) {
      System.out.println(e.toString());
      return TrajectoryGenerator.generateTrajectory(List.of(new Pose2d(), new Pose2d()), CONFIG);
    }
  }
}
