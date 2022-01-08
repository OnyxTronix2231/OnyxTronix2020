package robot.drivetrain;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

import java.util.List;

public class OnyxTrajectoryGenerator {
  public Trajectory getTrajectoryFromPoseList(final List<Pose2d> poses, final DriveTrainComponents components) {
    try {
      return TrajectoryGenerator.generateTrajectory(poses, components.getTrajectoryConfig());
    } catch (final Exception e) {
      System.out.println(e.toString());
      return TrajectoryGenerator.generateTrajectory(List.of(new Pose2d(), new Pose2d()), components.getTrajectoryConfig());
    }
  }
}
