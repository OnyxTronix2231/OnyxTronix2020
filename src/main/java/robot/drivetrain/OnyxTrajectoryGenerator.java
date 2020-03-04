package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import java.util.List;

public class OnyxTrajectoryGenerator {

  final TrajectoryConfig trajectoryConfig;

  public OnyxTrajectoryGenerator(final TrajectoryConfig trajectoryConfig) {
    this.trajectoryConfig = trajectoryConfig;
  }

  public Trajectory getTrajectoryFromPoseList(final List<Pose2d> poses, final DriveTrainComponents components,
                                              final boolean isForward) {
    trajectoryConfig.setReversed(!isForward);
//    try {
      return TrajectoryGenerator.generateTrajectory(poses, trajectoryConfig);
//    } catch (final Exception e) {
//      System.out.println(e.toString());
//      System.out.println("Failed Trajectory Generation");
//      return TrajectoryGenerator.generateTrajectory(List.of(new Pose2d(), new Pose2d()), components.getTrajectoryConfig());
//    }
  }
}